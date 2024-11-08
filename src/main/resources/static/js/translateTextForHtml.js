document.addEventListener("DOMContentLoaded", function () {
    // 포맷팅이 필요한 클래스 목록을 배열로 관리
    const classesToFormat = ['questionItem', 'descriptionText', 'message user', 'message bot'];

    // 초기 로딩된 요소에 대해 포맷팅 적용
    classesToFormat.forEach(classNames => {
        document.querySelectorAll(`.${classNames.replace(' ', '.')}`).forEach(element => {
            formatElement(element, classNames);
        });
    });

    // MutationObserver 설정(동적으로 추가되는 요소에 대해 포맷팅 적용)
    const observer = new MutationObserver(function (mutationsList) {
        mutationsList.forEach(function (mutation) {
            mutation.addedNodes.forEach(function (node) {
                if (node.nodeType === Node.ELEMENT_NODE) {
                    classesToFormat.forEach(classNames => {
                        const classList = classNames.split(' ');
                        // 모든 클래스가 있는지 확인
                        if (classList.every(className => node.classList.contains(className))) {
                            formatElement(node, classNames);
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
function formatElement(element, classNames) {

    if (classNames === 'message user') {
        formatTextWithLineBreakOnly(element);
    } else {
        formatElementText(element);
    }
}

function formatElementText(element) {
    // element의 텍스트를 줄바꿈으로 나누어 배열로 저장
    let lines = element.textContent.split('\n');

    // 각 줄을 처리하여 포맷팅
    let formattedLines = lines.map(line => {
        let formattedLine = line;

        // 줄의 시작 부분에서 #을 확인하여 대제목과 소제목으로 변환
        if (formattedLine.startsWith('# ')) {
            formattedLine = formattedLine.replace(/^# (.*)/, '<span style="font-weight: bold; font-size: 1.6em;">$1</span>');
        } else if (formattedLine.startsWith('## ')) {
            formattedLine = formattedLine.replace(/^## (.*)/, '<span style="font-weight: bold; font-size: 1.4em;">$1</span>');
        } else if (formattedLine.startsWith('### ')) {
            formattedLine = formattedLine.replace(/^### (.*)/, '<span style="font-weight: bold; font-size: 1.2em;">$1</span>');
        }

        // **로 둘러싸인 단어를 굵게 표시
        formattedLine = formattedLine.replace(/\*\*(.*?)\*\*/g, '<span style="font-weight: bold; font-size: 1em;">$1</span>');

        return formattedLine; // 포맷팅된 줄 반환
    });

    // 포맷팅된 줄들을 다시 줄바꿈으로 연결
    element.innerHTML = formattedLines.join('<br/>');
}


// message.user와 같이 줄바꿈만 적용
function formatTextWithLineBreakOnly(element) {
    element.innerHTML = element.textContent.replace(/\n/g, '<br/>');
}
