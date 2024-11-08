document.addEventListener("DOMContentLoaded", function () {
    // 포맷팅이 필요한 클래스 목록을 배열로 관리
    const classesToFormat = ['questionItem', 'descriptionText', 'message user', 'message bot'];

    // 초기 로딩된 요소에 대해 포맷팅 적용
    classesToFormat.forEach(classNames => {
        document.querySelectorAll(`.${classNames.replace(' ', '.')}`).forEach(element => {
            if (classNames === 'message user') {
                formatTextWithLineBreakOnly(element);
            } else {
                formatElementText(element);
            }
        });
    });

    // MutationObserver 설정(동적으로 추가되는 요소에 대해 포맷팅 적용)
    const observer = new MutationObserver(function (mutationsList) {
        mutationsList.forEach(function (mutation) {
            mutation.addedNodes.forEach(function (node) {
                if (node.nodeType === Node.ELEMENT_NODE) {
                    classesToFormat.forEach(classNames => {
                        const classList = classNames.split(' '); // 클래스 이름을 공백으로 나누어 배열로 만듭니다.
                        if (classList.every(className => node.classList.contains(className))) { // 모든 클래스를 체크합니다.
                            if (classNames === 'message user') {
                                formatTextWithLineBreakOnly(node);
                            } else {
                                formatElementText(node);
                            }
                        }
                    });
                }
            });
        });
    });

    // body 요소에 대해 DOM 변경사항 관찰 시작
    observer.observe(document.body, {childList: true, subtree: true});
});

// 포맷팅 함수
function formatElementText(element) {
    console.log("in!!" + element)
    let formattedText = element.textContent.replace(/\n/g, '<br/>');

    // #으로 시작하는 텍스트를 대제목과 소제목으로 변환
    formattedText = formattedText.replace(/^### (.*?)(<br\/>|$)/gm, '<span style="font-weight: bold; font-size: 1.2em;">$1</span>');
    formattedText = formattedText.replace(/^## (.*?)(<br\/>|$)/gm, '<span style="font-weight: bold; font-size: 1.4em;">$1</span>');
    formattedText = formattedText.replace(/^# (.*?)(<br\/>|$)/gm, '<span style="font-weight: bold; font-size: 1.6em;">$1</span>');

    // **로 둘러싸인 단어를 굵게 표시
    formattedText = formattedText.replace(/\*\*(.*?)\*\*/g, '<span style="font-weight: bold; font-size: 1em;">$1</span>');

    element.innerHTML = formattedText;
}


// message.user와 같이 줄바꿈만 적용
function formatTextWithLineBreakOnly(element) {
    element.innerHTML = element.textContent.replace(/\n/g, '<br/>');
}
