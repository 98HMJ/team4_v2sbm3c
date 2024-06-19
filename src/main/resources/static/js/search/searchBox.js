document.addEventListener('DOMContentLoaded', function () {
  const searchBox = document.getElementById('search-box');
  const results = document.getElementById('results');
  
  searchBox.addEventListener('input', function () {
    let query = this.value;
    if (query.length > 0) {
      fetch('/trash_linked/trash_list_search?word=' + encodeURIComponent(query))
        .then(response => response.json())
        .then(data => {
          results.innerHTML = '';
          results.style.display = 'block';
          data.forEach(item => {
            const resultItem = document.createElement('div');
            resultItem.className = 'result-item';
            resultItem.dataset.id = item.trashno;
            resultItem.innerHTML = `
                          <img src="/images/trash/storage/${item.thumb1}">
                          <span>${item.name}</span>
                      `;
            results.appendChild(resultItem);
          });
        });
    } else {
      results.style.display = 'none';
    }
  });

  // 결과 항목 클릭 시 해당 항목으로 이동
  results.addEventListener('click', function (event) {
    if (event.target.closest('.result-item')) {
      const trashId = event.target.closest('.result-item').dataset.id;
      window.location.href = `/trash/trash_read?trashno=${trashId}`;
    }
  });

  // 검색창과 결과 박스 외부 클릭 시 결과 박스 숨기기
  document.addEventListener('click', function (event) {
    if (!searchBox.contains(event.target) && !results.contains(event.target)) {
      results.style.display = 'none';
    }
  });

  // 검색창 클릭 시 결과 박스 표시 (검색창 비워져 있지 않은 경우)
  searchBox.addEventListener('focus', function () {
    if (searchBox.value.length > 0) {
      results.style.display = 'block';
    }
  });

});