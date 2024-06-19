document.addEventListener("DOMContentLoaded", () => {
  const topBtn = document.querySelector(".moveTopBtn");
  const topBtnLi = document.querySelector(".btn_li");

  function toggleTopButton() {
    // 현재 스크롤 위치 가져오기
    const scrollPosition = window.scrollY || document.documentElement.scrollTop;

    // 일정 스크롤 이상이면 버튼 표시
    if (scrollPosition > 799) {
      topBtn.style.display = "flex";
      topBtn.classList.add("container_1", "container_2");
      topBtnLi.classList.add("box", "box1");
    } else {
      topBtn.style.display = "none";
      topBtn.classList.remove("container_1", "container_2");
      topBtnLi.classList.remove("box", "box1");
    }
  }

  toggleTopButton();

  // 스크롤 이벤트 리스너 추가
  window.addEventListener("scroll", toggleTopButton);

  // 부드러운 스크롤 함수
  function smoothScrollToTop(duration) {
    const start = window.scrollY || document.documentElement.scrollTop;
    const startTime = 'now' in window.performance ? performance.now() : new Date().getTime();

    const easeOutQuad = (t) => t * (2 - t);

    function scroll() {
      const now = 'now' in window.performance ? performance.now() : new Date().getTime();
      const time = Math.min(1, ((now - startTime) / duration));
      const timeFunction = easeOutQuad(time);
      window.scrollTo(0, Math.ceil((timeFunction * (0 - start)) + start));

      if (window.scrollY !== 0) {
        requestAnimationFrame(scroll);
      }
    }

    scroll();
  }

  // 버튼 클릭 시 부드럽게 맨 위로 이동
  topBtn.onclick = () => {
    smoothScrollToTop(400); // 스크롤 시간을 1000ms (1초)로 설정
  };
});
