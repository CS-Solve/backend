document.addEventListener('DOMContentLoaded', function() {
    let selectedChoice = null; // 선택된 선택지 저장 변수

    // 모든 선택지 가져오기
    const choices = document.querySelectorAll('.choice-item');

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
    const checkAnswerButton = document.getElementById('checkButton');
    checkAnswerButton.addEventListener('click', function() {
        const answerBox = document.getElementById('answerBox');
        const answerText = document.getElementById('answerText');

        if (selectedChoice) {
            const isCorrect = selectedChoice.getAttribute('data-answer-status') === 'true'; // 정답 여부 확인

            // 정답 여부에 따라 결과 표시
            if (isCorrect) {
                answerBox.style.display = 'flex'; // 정답/오답 박스 표시
                answerBox.classList.add('correct-answer');
                answerBox.classList.remove('wrong-answer');
                answerText.textContent = '정답입니다!';
            } else {
                answerBox.style.display = 'flex'; // 정답/오답 박스 표시
                answerBox.classList.add('wrong-answer');
                answerBox.classList.remove('correct-answer');
                answerText.textContent = '오답입니다!';
            }
        } else {
            // 선택지가 선택되지 않았을 경우 경고 표시 (선택지가 없으면 처리)
            answerBox.style.display = 'flex';
            answerText.textContent = '선택지를 먼저 선택하세요!';
            answerBox.classList.add('wrong-answer');
            answerBox.classList.remove('correct-answer');
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