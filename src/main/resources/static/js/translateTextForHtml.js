document.addEventListener("DOMContentLoaded", function () {
    const descriptions = document.querySelectorAll('.questionItem');
    descriptions.forEach(function (element) {
        // 줄 바꿈을 <br/>로 변환
        let formattedText = element.textContent.replace(/\n/g, '<br/>');

        // **로 둘러싸인 단어를 굵게 표시
        formattedText = formattedText.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');

        // 최종적으로 HTML에 적용
        element.innerHTML = formattedText;
    });
});


/*
동적으로 추가되는 것에는 JS로 직접 해야함.처음 초기화된 것에만 eventListener가 적용됨
 */
