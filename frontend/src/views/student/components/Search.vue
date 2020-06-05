<template>
  <div>
    <div id="map" class="map"></div>
    <div class="list"></div>
  </div>
</template>

<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
let infowindow;
let map;
let ps;
export default {
  props: ["options"],
  data() {
    return {
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
      maxPage: 45
    };
  },
  mounted() {
    // console.log("여기는 Search");
    // console.log(this.options.options.radius);
    this.mapOptions.location.latitude = this.options.place.x;
    this.mapOptions.location.longitude = this.options.place.y;
    this.mapOptions.radius = this.options.options.radius;

    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
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
      for (let i = 1; i <= this.maxPage; ++i) {
        // 카테고리로 학원을 검색합니다
        ps.categorySearch("AC5", this.placesSearchCB, {
          x: this.mapOptions.location.latitude,
          y: this.mapOptions.location.longitude,
          radius: this.mapOptions.radius,
          page: i
        });
      }
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        for (let i = 0; i < data.length; ++i) {
          this.options.checked.forEach(value => {
            if (data[i].category_name.includes(value)) this.datas.push(data[i]);
          });
        }
        this.page += 1;
        if (this.page == this.maxPage) {
          // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
          // LatLngBounds 객체에 좌표를 추가합니다
          console.log(this.datas);
          let bounds = new kakao.maps.LatLngBounds();
          const set = new Set();
          for (let i = 0; i < this.datas.length; ++i) {
            set.add(this.datas[i].category_name.substring(13));
            let data = this.datas[i];
            this.displayMarker(data);
            bounds.extend(new kakao.maps.LatLng(data.y, data.x));
          }
          //   console.log(set);
          // // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
          map.setBounds(bounds);
        }
      }
    },
    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );
      // 마커를 생성하고 지도에 표시합니다
      var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage
      });

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "click", function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent(
          '<div style="padding:5px;font-size:12px;">' +
            place.place_name +
            "</div>"
        );
        infowindow.open(map, marker);
      });
    }
  }
};
</script>

<style>
.map {
  width: 100%;
  height: 400px;
}
</style>
