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
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=be28ab6abd772e2ef55ac5c2f1aa7792";
      document.head.appendChild(script);
    },
    initMap() {
      infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      let options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(36.119565, 128.344285), //지도의 중심좌표.
        level: 5 //지도의 레벨(확대, 축소 정도)
      };

      // 장소 검색 객체를 생성합니다
      ps = new kakao.maps.services.Places();
      // 키워드로 장소를 검색합니다
      ps.keywordSearch("구미시청", this.placesSearchCB);
      map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
      }
    },
    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x)
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