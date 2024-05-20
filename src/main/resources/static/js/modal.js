document.addEventListener('DOMContentLoaded', function () {
    
    var modalOpenButtons = document.querySelectorAll('.modal_open');
    var modal = document.getElementById('modal_image');

    modalOpenButtons.forEach(function(button) {
      button.addEventListener('click', function(event) {
        event.preventDefault();
        modal.style.display = 'flex';
        document.body.style.overflowY = "hidden"; // 스크롤 없애기
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
          }
        });
      
      });
      
    });

    // 모달 외부 클릭 시 모달 닫기
    window.addEventListener('click', function(event) {
      if (event.target === modal) {
        modal.style.display = 'none';
      }
      document.body.style.overflowY = "auto"; // 스크롤 없애기
    });
});