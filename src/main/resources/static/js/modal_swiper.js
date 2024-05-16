var mySwiper = new Swiper('.modalSwiper', {
    slidesPerView: 1, // 한 번에 표시할 슬라이드 수 3개
    spaceBetween: 10, // 슬라이드 간의 간격
    loop: true, // 슬라이드 루프(무한 회전) 활성화

    direction: 'horizontal', // 슬라이드 방향 (수평)
    touchRatio: 1, // 슬라이드 드래그 감도
    mousewheel: true, // 마우스 휠로 슬라이드 이동 가능
    centeredSlides: true, // 슬라이드 중앙 정렬
    watchOverflow: true, // 슬라이드가 화면을 넘어갈 때의 처리 설정

    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
});