<template>
  <div class="location">
    <div class="map_wrap">
      <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;">
        <div class="option">
          <form @submit="searchPlaces">
            <input
              type="text"
              v-model="keyword"
              placeholder="주변 지역 혹은 건물을 입력해 주세요."
              @keyup.enter="initInput"
            />
            <!-- <button type="submit">
              <i class="fas fa-search"></i>
            </button>-->
          </form>
        </div>
      </div>

      <div id="menu_wrap">
        <ul id="placesList"></ul>
        <div id="pagination"></div>
      </div>
    </div>
    <el-dialog :visible.sync="dialogVisible">
      <strong>{{place_name}} 주변으로 검색하시겠습니까?</strong>
      <div class="dialog-btn">
        <el-button @click="dialogVisible = false">취소</el-button>
        <el-button type="primary" @click="nextPage">계속</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
let markers = [];
let infowindow;
let map;
let ps;
export default {
  props: ["options"],
  data() {
    return {
      mapOptions: {
        location: {
          latitude: 37.513545,
          longitude: 126.940996
        },
        radius: 1000,
        level: 5,
        size: 15
      },
      datas: [],
      page: 0,
      maxPage: 45,
      keyword: "",
      dialogVisible: false,
      places: "",
      place_name: ""
    };
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    noSearchResult() {
      this.$message({
        message: "검색 결과가 존재하지 않습니다.",
        type: "warning"
      });
    },
    searchError() {
      this.$message.error("검색 결과 중 오류가 발생했습니다.");
    },
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=be28ab6abd772e2ef55ac5c2f1aa7792";
      document.head.appendChild(script);
    },
    initMap() {
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
      infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    },
    // 키워드 검색을 요청하는 함수입니다
    searchPlaces() {
      //   var keyword = document.getElementById("keyword").value;
      let keyword = this.keyword;
      // console.log(keyword);
      if (!keyword.replace(/^\s+|\s+$/g, "")) {
        alert("키워드를 입력해주세요!");
        return false;
      }
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      ps.keywordSearch(keyword, this.placesSearchCB);
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        this.displayPlaces(data);

        // 페이지 번호를 표출합니다
        this.displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        this.noSearchResult();
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        this.searchError();
        return;
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
    initInput() {
      this.keyword = "";
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
      };

      return el;
    },
    nextPage() {
      this.dialogVisible = false;
      this.$emit("setLocation", this.places);
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
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
      markers = [];
    },
    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    displayPagination(pagination) {
      var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = "on";
        } else {
          el.onclick = (function(i) {
            return function() {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },
    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

      infowindow.setContent(content);
      infowindow.open(map, marker);
    },
    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    }
  }
};
</script>


<style lang="scss">
.location {
  background-color: rgba(0, 0, 0, 0.1);
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 450px;
}
.map_wrap #map {
  position: fixed;
  top: 0px;
}
#menu_wrap {
  width: 100%;
  overflow-y: auto;
  background: rgba(0, 0, 0, 0.1);
  z-index: 1;
  font-size: 12px;
}
.option {
  width: 100%;
  text-align: center;
  z-index: 10;
  position: absolute;
  bottom: 0px;
  display: flex;
  justify-content: center;
}
.option form {
  width: 100%;
}
.option input {
  width: 100%;
  height: 40px;
  transition: width 0.5s ease-in-out, transform 0.5s ease-in-out,
    border-radius 0.5s ease-in-out;
  border: 0;
  text-align: center;
}
.option input:focus {
  width: 80%;
  height: 40px;
  bottom: 20px;
  border-radius: 10px;
  transform: translateY(-30px);
  text-align: center;
}
.option button {
  margin-left: 5px;
  height: 25px;
  padding: 0 10px;
  border-radius: 10px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
.el-dialog__wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  .el-dialog {
    margin: 0 !important;
    width: 80%;
    .dialog-btn {
      text-align: right;
      margin-top: 20px;
      button {
        padding: 8px 12px !important;
      }
    }
    .el-dialog__body {
      padding: 10px 20px 20px 20px;
    }
  }
}
</style>