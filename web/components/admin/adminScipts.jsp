<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="/assets/vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="/assets/vendor/bootstrap/js/tether.min.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="/assets/vendor/jquery/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="/assets/vendor/waves/waves.js"></script>
<!--Menu sidebar -->
<script src="/assets/vendor/slidebarmenu/sidebarmenu.js"></script>
<!--stickey kit -->
<script src="/assets/vendor/sticky-kit-master/dist/sticky-kit.min.js"></script>
<!--Custom JavaScript -->
<script src="/assets/vendor/custom/custom.min.js"></script>
<!-- ============================================================== -->
<!-- This page plugins -->
<!-- ============================================================== -->
<!-- chartist chart -->
<script src="/assets/vendor/chartist-js/dist/chartist.min.js"></script>
<script src="/assets/vendor/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
<!--c3 JavaScript -->
<script src="/assets/vendor/d3/d3.min.js"></script>
<script src="/assets/vendor/c3-master/c3.min.js"></script>
<!-- Chart JS -->
<script src="/assets/vendor/dashboard/dashboard1.js"></script>

<script>
    $(document).ready(() => {
        console.log('ready')
        $('#viewPassword').on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            const current = $('#password').attr('type')
            $('#password').attr('type', current === 'text' ? 'password' : 'text')
        })

    })
</script>
