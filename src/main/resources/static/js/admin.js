function toggleApprove(questionId) {
    fetch(`/admin/question/normal/toggle-approve/${questionId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to toggle approve');
            }
            return response.json();
        })
        .then(data => {
            console.log('Approve status toggled successfully:', data);
        })
        .catch(error => {
            console.error('Error toggling approve status:', error);
        });
}

// canBeMultiple 상태를 토글하는 함수
function toggleCanBeMultiple(questionId) {
    fetch(`/admin/question/normal/toggle-multiple/${questionId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to toggle canBeMultiple');
            }
            return response.json();
        })
        .then(data => {
            console.log('Can be multiple status toggled successfully:', data);
        })
        .catch(error => {
            console.error('Error toggling canBeMultiple status:', error);
        });
}