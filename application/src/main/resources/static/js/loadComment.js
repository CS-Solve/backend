function loadComments() {
    // sessionStorage에서 저장된 데이터 가져오기
    const savedQuestionData = JSON.parse(sessionStorage.getItem("lastSelectedQuestion"));
    const questionId = savedQuestionData ? savedQuestionData.questionId : null;

    if (!questionId) {
        alert("선택된 질문이 없습니다.");
        return;
    }

    // AJAX 요청
    $.ajax({
        url: `/question/${questionId}/comment`, // questionId를 URL에 포함
        method: 'GET',
        success: function (data) {
            // 이미 모달이 있다면 제거
            $('#commentModal').remove();
            // 새 모달 HTML을 추가하고 표시
            $('body').append(data);
            $('#commentModal').modal('show');

            // 모달이 로드된 후에 questionItem 요소들을 포맷팅
            $('#commentModal .questionItem').each(function () {
                formatElementText(this);
            });
        },
        error: function () {
            alert('댓글을 불러오는 데 실패했습니다.');
        }
    });
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
