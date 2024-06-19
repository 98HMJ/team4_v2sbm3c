document.addEventListener("DOMContentLoaded", () => {
  const dropdown = document.querySelector(".dropdown-content");
  const dropdownRect = dropdown.getBoundingClientRect();
  const windowWidth = window.innerWidth;

  if (dropdownRect.right > windowWidth) {
    dropdown.style.right = "0";
    dropdown.style.transform = "translateX(0)";
  }

  // 드롭다운 토글 함수
  function toggleDropdown() {
    document.getElementById("myDropdown").classList.toggle("show");
  }

  // 드롭다운이 열려 있을 때, 사용자가 다른 곳을 클릭하면 닫히도록 설정
  window.onclick = function (event) {
    if (!event.target.matches(".dropbtn")) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains("show")) {
          openDropdown.classList.remove("show");
        }
      }
    }
  };
});
