document.addEventListener('DOMContentLoaded', function () {
    
    var modalOpenButtons = document.querySelectorAll('.modal_open');
    var modal = document.getElementById('modal_image');

    modalOpenButtons.forEach(function(button) {
      button.addEventListener('click', function(event) {
        event.preventDefault();
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