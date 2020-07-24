<script>
    var html = ' <div class="row col-md-12">\n' +
        '                                            <div class="col-sm-5">\n' +
        '                                                <div class="row-cols-sm-5">URL\n' +
        '                                                    <input type="text"\n' +
        '                                                           class="form-control form-control-line"\n' +
        '                                                           name="image_URL"\n' +
        '                                                    <%--                                           value="<%=admin.getPhone()%>"--%>\n' +
        '                                                    >\n' +
        '                                                </div>\n' +
        '                                            </div>\n' +
        '                                            <div class="col-sm-5">\n' +
        '                                                <div class="row-cols-sm-5">Description\n' +
        '                                                    <input type="text"\n' +
        '                                                           class="form-control form-control-line"\n' +
        '                                                           name="image_description"\n' +
        '                                                    <%--                                           value="<%=admin.getPhone()%>"--%>\n' +
        '                                                    >\n' +
        '                                                </div>\n' +
        '                                            </div>\n' +
        '                                            <div class="col-sm-2"\n' +
        '                                                 style="display: flex; align-items: flex-end; margin-bottom: 5px">\n' +
        '                                                <button class="removeItem btn btn-primary" type="button">\n' +
        '                                                    Remove\n' +
        '                                                </button>\n' +
        '\n' +
        '                                            </div>\n' +
        '\n' +
        '                                        </div>'
    $(document).ready(function () {
        $(document).on('click', '.btn-show-create', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click')
            $('#tour_create').addClass('show');
        })
        $(document).on('click', '.close', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click')
            $('#tour_create').removeClass('show');
        })

        $(document).on('click', '.addItem', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click')
            $('#form-group-image').append(html)
        })

        $(document).on('click', '.removeItem', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click remove')
            $($($(e.target).parent()).parent()).remove()
        })

    });
    $(document).ready(function () {

    })
</script>
