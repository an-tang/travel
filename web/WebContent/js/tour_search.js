/************************************************/
/* Navigate to the search results page when user performs a search for tours */
/************************************************/

document.addEventListener('DOMContentLoaded', function () {
    var searchButton = document.getElementsByClassName('search_button')[0];
    var searchInput = document.getElementsByClassName('search_input')[0];

    searchButton.onclick = goToSearchResults;
    searchInput.addEventListener('keypress', function (e) {
        if (e.which == 13) { // Enter press
            e.preventDefault();
            goToSearchResults();
        }
    });

    function goToSearchResults() {
        var keyword = searchInput.value.trim();
        if (isValid(keyword)) {
            var queryString = "?q=" + encodeURI(keyword);
            var url = "search_results.html" + queryString;
            window.location.href = url;
        } else {
            alert('Vui lòng nhập từ khóa hợp lệ!');
        }
    };

    function isValid(keyword) {
        if (keyword.replace(/\s/g, "") === "") return false;

        if (keyword.includes("?") ||
            keyword.includes("=") ||
            keyword.includes("!") ||
            keyword.includes("@") ||
            keyword.includes("#") ||
            keyword.includes("+") ||
            keyword.includes("&") ||
            keyword.includes("/") ||
            keyword.includes("%"))
            return false;

        return true;
    }
});