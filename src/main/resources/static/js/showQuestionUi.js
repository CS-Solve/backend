document.addEventListener('DOMContentLoaded', function() {
    const backButton = document.querySelector('.mainPageFrame');

    backButton.style.cursor = 'pointer'; // 마우스를 올리면 클릭할 수 있다는 느낌을 주기 위해 포인터 추가

    backButton.addEventListener('click', function() {
        window.location.href = '/'; // 메인화면으로 이동
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // 모든 level-show-box 요소 가져오기
    const levelBoxes = document.querySelectorAll('.level-show-box');

    // 각 level-show-box에 대해 반복
    levelBoxes.forEach(function(levelBox) {
        // 각 level-show-box의 data-level 속성에서 난이도 값 가져오기
        const level = levelBox.getAttribute('data-level');

        // 난이도에 따라 배경색 설정
        if (level === '상') {
            levelBox.style.backgroundColor = '#ff6b6b'; // 빨강 (상)
        } else if (level === '중') {
            levelBox.style.backgroundColor = '#ffd85099'; // 노랑 (중)
        } else if (level === '하') {
            levelBox.style.backgroundColor = '#6bafff'; // 파랑 (하)
        }
    });
});