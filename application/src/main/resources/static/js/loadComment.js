function loadComments() {
    $.ajax({
        url: '/question/171/comment', // 백엔드에서 commentModal.html을 반환
        method: 'GET',
        success: function (data) {
            // 이미 모달이 있다면 제거
            $('#commentModal').remove();

            // 새 모달 HTML을 추가하고 표시
            $('body').append(data);
            $('#commentModal').modal('show');
        },
        error: function () {
            alert('댓글을 불러오는 데 실패했습니다.');
        }
    });
}
