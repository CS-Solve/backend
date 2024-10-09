document.addEventListener("DOMContentLoaded", function() {
    const descriptions = document.querySelectorAll('.descriptionText, #descriptionText, .questionItem');
    descriptions.forEach(function(element) {
        element.innerHTML = element.textContent.replace(/\n/g, '<br/>');
    });
});