function init() {
    $('#query').autocomplete({
        serviceUrl: '/autocomplete',
        paramName: 'q',
        noCache: true
    });
    
    $('#btnTest').on('click', function() {
        window.location.href = '/test';
    });
}

function go(url) {
    window.location.href = url;
}

function search(query) {
    window.location.href = 'search?query='+query;
}
