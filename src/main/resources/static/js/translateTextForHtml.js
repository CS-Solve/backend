document.addEventListener("DOMContentLoaded", function() {
    const descriptions = document.querySelectorAll('#questionItem, .description, #descriptionText, .questionTitle');
    descriptions.forEach(function(element) {
        element.innerHTML = element.textContent.replace(/\n/g, '<br/>');
    });
});