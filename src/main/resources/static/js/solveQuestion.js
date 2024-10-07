document.addEventListener('DOMContentLoaded', function() {
    const checkButton = document.getElementById('checkButton');
    const answerBox = document.getElementById('answerBox');
    const answerText = document.getElementById('answerText');

    // 가상의 데이터 예시 (정답 상태를 설정)
    const questionData = {
        answerStatus: true // true면 정답, false면 오답
    };

    checkButton.addEventListener('click', function() {
        answerBox.style.display = 'flex'; // 정답 또는 오답 박스를 표시

        if (questionData.answerStatus) {
            // 정답일 때
            answerBox.classList.add('correct-answer');
            answerBox.classList.remove('wrong-answer');
            answerText.textContent = '정답입니다!';
        } else {
            // 오답일 때
            answerBox.classList.add('wrong-answer');
            answerBox.classList.remove('correct-answer');
            answerText.textContent = '오답입니다!';
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