document.addEventListener('DOMContentLoaded', function() {
    let selectedChoice = null; // 선택된 선택지 저장 변수
    const choices = document.querySelectorAll('.choice-item'); // 모든 선택지 가져오기
    const checkAnswerButton = document.getElementById('checkButton'); // 채점 버튼
    const answerBox = document.getElementById('answerBox'); // 정답/오답 표시 박스
    const answerText = document.getElementById('answerText'); // 정답/오답 텍스트
    const descriptionBox = document.querySelector('.description-box'); // 해설 보기 버튼 박스
    const toggleButton = document.getElementById("toggleDescription"); // 해설 보기 버튼
    const descriptionContent = document.getElementById("descriptionContent"); // 해설 내용 표시 박스
    const descriptionText = document.getElementById("descriptionText"); // 해설 텍스트

    // 기본적으로 해설 버튼을 숨겨둠
    descriptionBox.style.display = "none";
    toggleButton.style.display = "none";

    // 선택지 클릭 시 선택 처리
    choices.forEach(choice => {
        choice.addEventListener('click', function() {
            // 기존 선택지 해제 (선택된 스타일 제거)
            if (selectedChoice) {
                selectedChoice.classList.remove('selected-choice');
            }

            // 새로 선택된 선택지 설정
            selectedChoice = choice;
            choice.classList.add('selected-choice'); // 선택된 스타일 추가
        });
    });

    // 채점 버튼 클릭 시 마지막 선택지의 정답 여부 확인
    checkAnswerButton.addEventListener('click', function() {
        if (selectedChoice) {
            const isCorrect = selectedChoice.getAttribute('data-answer-status') === 'true';
            const selectText = selectedChoice.getAttribute('data-choice-text');// 정답 여부 확인

            // 정답 여부에 따라 결과 표시
            if (isCorrect) {
                answerBox.style.display = 'flex'; // 정답/오답 박스 표시
                answerBox.classList.add('correct-answer');
                answerBox.classList.remove('wrong-answer');
                answerText.textContent = '정답입니다!'+' ('+selectText+")";
            } else {
                answerBox.style.display = 'flex'; // 정답/오답 박스 표시
                answerBox.classList.add('wrong-answer');
                answerBox.classList.remove('correct-answer');
                answerText.textContent = '오답입니다!'+' ('+selectText+")";
            }

            // 정답 확인 후 해설 보기 버튼 보이기
            toggleButton.style.display = "block";
            descriptionBox.style.display = "block"; // 해설 보기 버튼 보이기

            // 선택된 질문의 설명을 가져와서 descriptionBox에 표시
            const description = selectedChoice.closest('.each-question').getAttribute('data-description');
            descriptionText.textContent = description; // descriptionText에 해설 표시
        } else {
            // 선택지가 선택되지 않았을 경우 경고 표시
            answerBox.style.display = 'flex';
            answerText.textContent = '선택지를 먼저 선택하세요!';
            answerBox.classList.add('wrong-answer');
            answerBox.classList.remove('correct-answer');
        }
    });

    // 해설 보기 버튼 클릭 시 해설 내용을 토글로 보이거나 숨기기
    toggleButton.addEventListener("click", function() {
        if (descriptionContent.style.display === "none") {
            descriptionContent.style.display = "block"; // 해설을 보이게 함
            toggleButton.textContent = "해설 숨기기 ▲"; // 버튼 텍스트 변경
        } else {
            descriptionContent.style.display = "none"; // 해설을 숨김
            toggleButton.textContent = "해설 보기 ▼"; // 버튼 텍스트 변경
        }
    });
});

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