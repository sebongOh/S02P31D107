<template>
  <div>
    <div id="map" class="map"></div>
    <div id="menu_wrap">
      <ul id="placesList"></ul>
      <div id="pagination"></div>
    </div>
  </div>
</template>

<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
let infowindow;
let map;
let ps;
let markers = [];
export default {
  props: ["options"],
  data() {
    return {
      clickList: {},
      markerClick: false,
      mapOptions: {
        location: {
          latitude: 0,
          longitude: 0
        },
        radius: 1000,
        level: 5,
        size: 15
      },
      datas: [],
      page: 0,
      classifiedData: []
    };
  },
  mounted() {
    // console.log("여기는 Search");
    // console.log(this.options);
    this.mapOptions.location.latitude = this.options.place.x;
    this.mapOptions.location.longitude = this.options.place.y;
    this.mapOptions.radius = this.options.options.radius;

    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    detail(clicklist) {
      this.$router.push({
        name: "academyDetail",
        params: {
          academyId: clicklist.id,
          name: clicklist.place_name,
          address: clicklist.road_address_name,
          category: clicklist.category_name,
          phone: clicklist.phone
        }
      });
    },
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=be28ab6abd772e2ef55ac5c2f1aa7792";
      document.head.appendChild(script);
    },
    initMap() {
      //   console.log("initMap 호출");
      //   console.log(this.mapOptions);
      infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      let options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(
          this.mapOptions.location.latitude,
          this.mapOptions.location.longitude
        ), //지도의 중심좌표.
        level: this.mapOptions.level //지도의 레벨(확대, 축소 정도)
      };
      map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
      // 장소 검색 객체를 생성합니다
      ps = new kakao.maps.services.Places();
      ps.categorySearch("AC5", this.placesSearchCB, {
        x: this.mapOptions.location.latitude,
        y: this.mapOptions.location.longitude,
        radius: this.mapOptions.radius
      });
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        console.log(data);
        console.log(status);
        console.log(pagination);

        for (let i = 0; i < data.length; ++i) this.datas.push(data[i]);
        // 있으면 다음 페이지를 검색한다.
        if (pagination.hasNextPage) {
          pagination.nextPage();
        }
        //다음 페이지가 없는 경우
        else {
          console.log(this.datas);
          let sample = this.options.options.selected;
          let set = new Set();
          for (let i = 0; i < sample.length; ++i) {
            let tmp = sample[i].split(" - #");
            set.add(tmp[0]);
            set.add(tmp[1]);
          }

          for (let i = 0; i < this.datas.length; ++i) {
            let tdata = this.datas[i];
            for (let item of set) {
              if (tdata.category_name.includes(item)) {
                this.classifiedData.push(tdata);
                break;
              }
            }
          }
          console.log(this.classifiedData);
          if (this.classifiedData.length === 0) {
            this.$message({
              message: "검색 결과가 존재하지 않습니다. 다시 검색해 주세요",
              type: "warning"
            });
            this.$emit("prevPage", 5);
          } else {
            // 검색 목록과 마커를 표출합니다
            this.displayPlaces(this.classifiedData);
          }
        }
      }
    },
    // 검색 결과 목록과 마커를 표출하는 함수입니다
    displayPlaces(places) {
      var listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu_wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds(),
        listStr = "";

      // 검색 결과 목록에 추가된 항목들을 제거합니다
      this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
          marker = this.addMarker(placePosition, i),
          itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        ((marker, title) => {
          kakao.maps.event.addListener(marker, "mouseover", () =>
            this.displayInfowindow(marker, title)
          );

          kakao.maps.event.addListener(marker, "mouseout", function() {
            infowindow.close();
          });

          itemEl.onmouseover = () => this.displayInfowindow(marker, title);

          itemEl.onmouseout = function() {
            infowindow.close();
          };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);
    },
    // 검색결과 항목을 Element로 반환하는 함수입니다
    getListItem(index, places) {
      var el = document.createElement("li"),
        itemStr =
          '<span class="markerbg marker_' +
          (index + 1) +
          '"></span>' +
          '<div class="info">' +
          "   <h5>" +
          places.place_name +
          "</h5>";

      if (places.road_address_name) {
        itemStr +=
          "    <span>" +
          places.road_address_name +
          "</span>" +
          '   <span class="jibun gray">' +
          places.address_name +
          "</span>";
      } else {
        itemStr += "    <span>" + places.address_name + "</span>";
      }

      itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

      el.innerHTML = itemStr;
      el.className = "item";
      el.onclick = () => {
        this.place_name = places.place_name;
        this.dialogVisible = true;
        this.places = places;
        var router = this.$router;
            router.push({
            name: "academyDetail",
            params: {
              academyId:places.id,
              name:places.place_name,
              address:places.road_address_name,
              phone:places.phone,
              category:places.category_name
            }
          });
      };

      return el;
    },
    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place, idx) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        );
      // 마커를 생성하고 지도에 표시합니다
      var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage
      });
    },
    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    },
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
      markers = [];
    },
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, idx, title) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage
        });

      marker.setMap(map); // 지도 위에 마커를 표출합니다
      markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

      infowindow.setContent(content);
      infowindow.open(map, marker);
    }
  }
};
</script>

<style lang='scss'>
#menu_wrap {
  width: 100%;
  overflow-y: auto;
  background: rgba(0, 0, 0, 0.1);
  z-index: 1;
  font-size: 13px;
  #placesList {
    padding: 10px !important;
    .item {
      .info {
        h5 {
          margin: 0 !important;
        }
      }
    }
  }
}

.info .tel {
  color: #009900;
}
.item .info {
  padding: 0 0 10px 55px;
}
.item span {
  display: block;
}
.item h4,
.item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 0;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
.item .marker_1 {
  background-position: 0 -10px;
}
li {
  list-style: none;
}
.item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
.map {
  width: 100%;
  height: 350px;
}
</style>
