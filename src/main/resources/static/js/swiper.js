var mySwiper = new Swiper('.mySwiper', {
  slidesPerView: 3, // 한 번에 표시할 슬라이드 수 3개
  spaceBetween: 20, // 슬라이드 간의 간격
  loop: true, // 슬라이드 루프(무한 회전) 활성화
  
  pagination: {
      el: '.swiper-pagination', // 페이지 표시기
      clickable: true // 페이지 번호 클릭 가능하게 설정
  },
  autoplay: {
      delay: 3000, // 3초마다 자동 재생
      disableOnInteraction: false // 사용자 상호 작용 후에도 자동 재생 유지
  },
  direction: 'horizontal', // 슬라이드 방향 (수평)
  touchRatio: 1, // 슬라이드 드래그 감도
  mousewheel: true, // 마우스 휠로 슬라이드 이동 가능
  centeredSlides: true, // 슬라이드 중앙 정렬
  watchOverflow: true // 슬라이드가 화면을 넘어갈 때의 처리 설정
});
