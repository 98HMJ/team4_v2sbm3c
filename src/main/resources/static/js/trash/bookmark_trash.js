document.addEventListener('DOMContentLoaded', () => {
    const trashNo = /*[[${trashVO.trashno}]]*/ '0';
    let bookmarkIcon = document.getElementById('bookmark-icon');

    // 북마크 상태를 확인하는 함수
    function checkBookmark() {
        const goUrl = '/bookmark/check_trash';
    
        fetch(goUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({trashno: trashNo}),
        })
        .then(response => response.json())
        .then(data => {
            if (data.res == 1) {
                bookmarkIcon.src = '/images/bookmark_enable.svg';
            } else {
                bookmarkIcon.src = '/images/bookmark_unable.svg';
            }
        })
        .catch(error => console.error('Error:', error));
    }

    checkBookmark();
          
    document.getElementById('bookmark-icon').addEventListener('click', () => {

        let bookmarkIcon = document.getElementById('bookmark-icon');

        let url = '/trash/trash_read?trashno=' + trashNo;
        const data = {url: url, trashno: trashNo};

        // 2. bookmark 버튼 누르면
        // - 2. 북마크에 추가 안되 있다면 // 이미지 비활성화시
        //  - a. 북마크에 추가후
        //  - b. 이미지 변경
        // Check the current state of the bookmark icon
        if (bookmarkIcon.src.includes('/images/bookmark_unable.svg')) {
          goUrl = '/bookmark/create_trash?trashno=' + trashNo;
          fetch(goUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data),
          })
            .then(response => response.json())
            .then(data => {
              if (data.res >= 1) {
                // Update the bookmark icon to the enabled state
                bookmarkIcon.src = '/images/bookmark_enable.svg';
              } else {
                console.error('Bookmark operation failed:', data.message);
              }
            })
            .catch(error => {
              console.error('Error:', error);
            });
        } else {
          // - 1. 북마크에 추가되어있지 않았다면
          //  - a. 북마크에 추가후
          //  - b. 이미지 변경                
            goUrl = '/bookmark/delete_trash';
          fetch(goUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({trashno: trashNo}),
          })
            .then(response => response.json())
            .then(data => {
              if (data.res >= 1) {
                bookmarkIcon.src = '/images/bookmark_unable.svg';
                console.log('Bookmark is set, removing bookmark...');
              } else {
              }
            })
            .catch(error => {
              console.error('Error:', error);
            });
        }
      });

  })
