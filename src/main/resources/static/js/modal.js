document.addEventListener('DOMContentLoaded', function() {

  // expno 에 따라서 data를 로드하는 함수
  async function loadDatas(expno) {
    const goUrl = '/trash_exploration/read?expno=' + expno;

    try {
      const response = await fetch(goUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
      });

      const data = await response.json();

      // console.log(data.images);
      const modalSwiperWrapper = document.getElementById('popupGallery').querySelector('.swiper-wrapper');
      modalSwiperWrapper.innerHTML = ''; // 기존 슬라이드 제거

      data.images.forEach(imageUrl => {
        // console.log(imageUrl);
        const slideDiv = document.createElement('div');
        slideDiv.className = 'swiper-slide';
        slideDiv.innerHTML = `<img src="${imageUrl}">`;
        modalSwiperWrapper.appendChild(slideDiv);
      });

      // Swiper 초기화
      initializeSwiper();

    } catch (error) {
      console.error('Error:', error);
    }
  }

  // 슬라이드의 위치에 따라 네비게이션 버튼의 표시 여부를 조정하는 함수
  function checkNavButtons(swiper) {
    const nextButton = swiper.el.querySelector('.custom-swiper-button-next');
    const prevButton = swiper.el.querySelector('.custom-swiper-button-prev');

    if (swiper.isEnd) {
      nextButton.style.display = 'none';
    } else {
      nextButton.style.display = 'block';
    }

    if (swiper.isBeginning) {
      prevButton.style.display = 'none';
    } else {
      prevButton.style.display = 'block';
    }
  }
  
  let popupGallery;
  var modalOpenButtons = document.querySelectorAll('.modal_open');
  const myModal = document.getElementById('myModal');

  function initializeSwiper() {
    if (popupGallery) {
      popupGallery.destroy(true, true); // 기존 Swiper 인스턴스 정리
    }

    popupGallery = new Swiper('#popupGallery', {
      slidesPerView: 1, // 한 번에 표시할 슬라이드 수 1개
      spaceBetween: 30, // 슬라이드 간의 간격 (30px)
      centeredSlides: true, // 슬라이드 중앙 정렬
      direction: 'horizontal', // 슬라이드 방향 (수평)
      observer: true,
      observeParents: true,
      navigation: {
        nextEl: '.custom-swiper-button-next',
        prevEl: '.custom-swiper-button-prev',
      },
      watchOverflow: true,
      on: {
        init: function() {
          checkNavButtons(this);
        },
        slideChange: function() {
          checkNavButtons(this);
        }
      },
    });
    
    popupGallery.update(); // Swiper 업데이트
  }
  
  modalOpenButtons.forEach(function(button) {
    button.addEventListener('click', function(event) {
      event.preventDefault();
      
      const expno = button.getAttribute('data-expno'); // expno 값을 가져옴

      loadDatas(expno);
      
      // Show the modal
      const modalInstance = new bootstrap.Modal(myModal); // Ensure bootstrap.Modal is defined
      modalInstance.show();

      document.body.style.overflowY = "hidden"; // 스크롤 없애기

    });
  });

  
  // 모달이 보여질 때 Swiper 업데이트
  myModal.addEventListener('shown.bs.modal', function() {
    // popupGallery.update();
  });

  // 모달이 닫힐 때 이벤트
  myModal.addEventListener('hidden.bs.modal', function () {
    myModal.hide();
    document.body.style.overflowY = ""; // 스크롤 복원
  });

});