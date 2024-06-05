document.addEventListener('DOMContentLoaded', function () {
//    var modalOpenButtons = document.querySelectorAll('.modal_open');
    
    // expno 에 따라서 모달 창을 여는 함수
    function openModal(expno) {
        const goUrl = '/trash_exploration/read?expno=' + expno;
        
        fetch(goUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        })
        .then(response => response.json())
        .then(data => {
            
            console.log(data.images);
             var modalSwiperWrapper = document.getElementById('modal-swiper-wrapper');
             modalSwiperWrapper.innerHTML = ''; // 기존 슬라이드 제거
            
            data.images.forEach(imageUrl => {
                console.log(imageUrl);
                var slideDiv = document.createElement('div');
                slideDiv.className = 'swiper-slide';
                slideDiv.innerHTML = `<img th:src="${imageUrl}">`;
                modalSwiperWrapper.appendChild(slideDiv);
            });
            
            var mySwiper = new Swiper('.modalSwiper', {
              slidesPerView: 1, // 한 번에 표시할 슬라이드 수 3개
              spaceBetween: 10, // 슬라이드 간의 간격
              loop: true, // 슬라이드 루프(무한 회전) 활성화
          
              direction: 'horizontal', // 슬라이드 방향 (수평)
              centeredSlides: true, // 슬라이드 중앙 정렬
              watchOverflow: true, // 슬라이드가 화면을 넘어갈 때의 처리 설정
          
              navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
              }
          });
        })
        .catch(error => console.error('Error:', error));
    }    
        
    var modalOpenButtons = document.querySelectorAll('.modal_open');
    var modal = document.getElementById('modal_image');

    modalOpenButtons.forEach(function(button) {
      button.addEventListener('click', function(event) {
        event.preventDefault();
        const expno = button.getAttribute('data-expno'); // expno 값을 가져옴
        
        openModal(expno);
        
        modal.style.display = 'flex';
        document.body.style.overflowY = "hidden"; // 스크롤 없애기
        
      
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