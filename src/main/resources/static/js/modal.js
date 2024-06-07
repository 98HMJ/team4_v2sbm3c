document.addEventListener('DOMContentLoaded', function() {

  // expno 에 따라서 모달 창을 여는 함수
  function loadDatas(expno) {
    const goUrl = '/trash_exploration/read?expno=' + expno;

    fetch(goUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
    })
      .then(response => response.json())
      .then(data => {

        console.log(data.images);
        var modalSwiperWrapper = document.getElementById('popupGallery').querySelector('.swiper-wrapper');
        modalSwiperWrapper.innerHTML = ''; // 기존 슬라이드 제거

        data.images.forEach(imageUrl => {
          console.log(imageUrl);
          var slideDiv = document.createElement('div');
          slideDiv.className = 'swiper-slide';
          slideDiv.innerHTML = `<img src="${imageUrl}">`;
          modalSwiperWrapper.appendChild(slideDiv);
        });

        popupGallery.update(); // Swiper 업데이트

      })
      .catch(error => console.error('Error:', error));
  }


  let popupGallery = new Swiper('#popupGallery', {
    slidesPerView: 1, // 한 번에 표시할 슬라이드 수 1개
    spaceBetween: 30, // 슬라이드 간의 간격 (30px)
    centeredSlides: true, // 슬라이드 중앙 정렬
    watchOverflow : true, // 슬라이드가 1개 일 때 pager, button 숨김 여부 설정
    direction: 'horizontal', // 슬라이드 방향 (수평)
    watchOverflow: true, // 슬라이드가 화면을 넘어갈 때의 처리 설정

    observer: true,
    observeParents: true,
  });
  
  document.querySelector('.popupGalleryNext').addEventListener('click', function() {
    popupGallery.slideNext();
  });

  document.querySelector('.popupGalleryPrev').addEventListener('click', function() {
    popupGallery.slidePrev();
  });

  var modalOpenButtons = document.querySelectorAll('.modal_open');
  const myModal = document.getElementById('myModal');

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
    popupGallery.update();
  });
  
  // 모달 창을 닫는 버튼에 대한 JavaScript 코드
  document.querySelector('.btn-close').addEventListener('click', function () {
    myModal.hide();
    document.body.style.overflowY = ""; // 스크롤 복원
  });

  // 모달이 닫힐 때 이벤트
  myModal.addEventListener('hidden.bs.modal', function () {
    document.body.style.overflowY = ""; // 스크롤 복원
  });

});