const topBtn = document.querySelector(".moveTopBtn");

// 스크롤 이벤트 리스너 추가
window.addEventListener("scroll", toggleTopButton);

function toggleTopButton() {
  // 현재 스크롤 위치 가져오기
  const scrollPosition = window.pageYOffset || document.documentElement.scrollTop;

  // 일정 스크롤 이상이면 버튼 표시
  if (scrollPosition > 799) {
    topBtn.style.display = "flex";
  } else {
    topBtn.style.display = "none !important";
  }
}

// 버튼 클릭 시 맨 위로 이동
topBtn.onclick = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};