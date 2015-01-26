/**
 * 翻页控件
 */
(function redirectPage () {
    'use strict';
    var $page = $('.pagination').find('a');
    $page.on('click', function (e) {
        e.preventDefault();
        var $me = $(this);
        var page = $me.attr('data-page') || $me.text();
        if (page) {
            redirect(page);
        }
    });
    function redirect (page) {
        var url = location.href;
        var separator = url.indexOf('?') === -1 ? '?' : '&';
        var hasPage = url.indexOf('pageNo=') === -1 ? false : true;
        var newPage = 'pageNo=' + page;
        if (hasPage) {
            var currentPage = url.slice(url.indexOf('pageNo='));
            var lastChat = currentPage.indexOf('&') === -1 ? currentPage.length : currentPage.indexOf('&');
            currentPage = currentPage.slice(0, lastChat);
            url = url.replace(currentPage, newPage);
        } else {
            url = url + separator + newPage;
        }
        window.location.href = url;
    }
}());
