<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description"
	content="Sentir, Responsive admin and dashboard UI kits template">
<meta name="keywords"
	content="admin,bootstrap,template,responsive admin,dashboard template,web apps template">
<meta name="author"
	content="Ari Rusmanto, Isoh Design Studio, Warung Themes">
<title>创建数字身份ID</title>

<!-- BOOTSTRAP CSS (REQUIRED ALL PAGE)-->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">

<!-- PLUGINS CSS -->
<link href="assets/plugins/weather-icon/css/weather-icons.min.css"
	rel="stylesheet">
<link href="assets/plugins/prettify/prettify.min.css" rel="stylesheet">
<link href="assets/plugins/magnific-popup/magnific-popup.min.css"
	rel="stylesheet">
<link href="assets/plugins/owl-carousel/owl.carousel.min.css"
	rel="stylesheet">
<link href="assets/plugins/owl-carousel/owl.theme.min.css"
	rel="stylesheet">
<link href="assets/plugins/owl-carousel/owl.transitions.min.css"
	rel="stylesheet">
<link href="assets/plugins/chosen/chosen.min.css" rel="stylesheet">
<link href="assets/plugins/icheck/skins/all.css" rel="stylesheet">
<link href="assets/plugins/datepicker/datepicker.min.css"
	rel="stylesheet">
<link href="assets/plugins/timepicker/bootstrap-timepicker.min.css"
	rel="stylesheet">
<link href="assets/plugins/validator/bootstrapValidator.min.css"
	rel="stylesheet">
<link href="assets/plugins/summernote/summernote.min.css"
	rel="stylesheet">
<link href="assets/plugins/markdown/bootstrap-markdown.min.css"
	rel="stylesheet">
<link href="assets/plugins/datatable/css/bootstrap.datatable.min.css"
	rel="stylesheet">
<link href="assets/plugins/morris-chart/morris.min.css" rel="stylesheet">
<link href="assets/plugins/c3-chart/c3.min.css" rel="stylesheet">
<link href="assets/plugins/slider/slider.min.css" rel="stylesheet">

<!-- MAIN CSS (REQUIRED ALL PAGE)-->
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>

<body class="login tooltips">




	<!--
		===========================================================
		BEGIN PAGE
		===========================================================
		-->
	<div class="login-header text-center">
		<img src="assets/img/logo-login.png" class="logo" alt="Logo">
	</div>
	<div class="login-wrapper">
		<form role="form">
			<div class="form-group">
				<div class="checkbox">
					<label class="inline-popups"> <input type="checkbox"
						class="i-yellow-flat"> 我同意 <a href="#text-popup"
						data-effect="mfp-zoom-in">该条款和条件</a>
					</label>
				</div>
			</div>
			<div class="form-group">
				<button type="button"
					class="btn btn-warning btn-lg btn-perspective btn-block"
					onclick="createEID()">创建数字身份ID</button>
			</div>
			<div id="ok" style="display:none;">
				<div class="form-group has-feedback lg left-feedback no-label">
					<input type="text" id="publicKey"
						class="form-control no-border input-lg rounded" placeholder="公钥"
						autofocus> <span class="fa fa-male form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback lg left-feedback no-label">
					<input type="text" id="privateKey"
						class="form-control no-border input-lg rounded"
						placeholder="***私钥****"> <span
						class="fa fa-user form-control-feedback"></span>
				</div>
				<div class="form-group">
					<a href="/kyc/identity.do">
						<button type="button"
							class="btn btn-warning btn-lg btn-perspective btn-block">确定</button>
					</a>
				</div>
			</div>

		</form>
	</div>
	<!-- /.login-wrapper -->


	<!-- Text popup -->
	<div id="text-popup" class="white-popup wide mfp-with-anim mfp-hide">
		<h4>条约</h4>
		<p>
			重视用户的隐私。您在使用我们的服务时，我们可能会收集和使用您的相关信息。我们希望通过本《隐私政策》向您说明，在使用我们的服务时，我们如何收集、使用、储存和分享这些信息，以及我们为您提供的访问、更新、控制和保护这些信息的方式。本《隐私政策》与您所使用的腾讯服务息息相关，希望您仔细阅读，在需要时，按照本《隐私政策》的指引，作出您认为适当的选择。本《隐私政策》中涉及的相关技术词汇，我们尽量以简明扼要的表述，并提供进一步说明的链接，以便您的理解。
		</p>
		<p>
			重视用户的隐私。您在使用我们的服务时，我们可能会收集和使用您的相关信息。我们希望通过本《隐私政策》向您说明，在使用我们的服务时，我们如何收集、使用、储存和分享这些信息，以及我们为您提供的访问、更新、控制和保护这些信息的方式。本《隐私政策》与您所使用的腾讯服务息息相关，希望您仔细阅读，在需要时，按照本《隐私政策》的指引，作出您认为适当的选择。本《隐私政策》中涉及的相关技术词汇，我们尽量以简明扼要的表述，并提供进一步说明的链接，以便您的理解。
		</p>
		<hr />
		<h4>条件</h4>
		<p>
			我们提供服务时，可能会收集、储存和使用下列与您有关的信息。如果您不提供相关信息，可能无法注册成为我们的用户或无法享受我们提供的某些服务，或者无法达到相关服务拟达到的效果。
		</p>
		<p>
			我们提供服务时，可能会收集、储存和使用下列与您有关的信息。如果您不提供相关信息，可能无法注册成为我们的用户或无法享受我们提供的某些服务，或者无法达到相关服务拟达到的效果。
		</p>
	</div>
	<!--
		===========================================================
		END PAGE
		===========================================================
		-->

	<!--
		===========================================================
		Placed at the end of the document so the pages load faster
		===========================================================
		-->
	<!-- MAIN JAVASRCIPT (REQUIRED ALL PAGE)-->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/plugins/retina/retina.min.js"></script>
	<script src="assets/plugins/nicescroll/jquery.nicescroll.js"></script>
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/backstretch/jquery.backstretch.min.js"></script>

	<!-- PLUGINS -->
	<script src="assets/plugins/skycons/skycons.js"></script>
	<script src="assets/plugins/prettify/prettify.js"></script>
	<script
		src="assets/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script src="assets/plugins/owl-carousel/owl.carousel.min.js"></script>
	<script src="assets/plugins/chosen/chosen.jquery.min.js"></script>
	<script src="assets/plugins/icheck/icheck.min.js"></script>
	<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="assets/plugins/timepicker/bootstrap-timepicker.js"></script>
	<script src="assets/plugins/mask/jquery.mask.min.js"></script>
	<script src="assets/plugins/validator/bootstrapValidator.min.js"></script>
	<script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
	<script src="assets/plugins/datatable/js/bootstrap.datatable.js"></script>
	<script src="assets/plugins/summernote/summernote.min.js"></script>
	<script src="assets/plugins/markdown/markdown.js"></script>
	<script src="assets/plugins/markdown/to-markdown.js"></script>
	<script src="assets/plugins/markdown/bootstrap-markdown.js"></script>
	<script src="assets/plugins/slider/bootstrap-slider.js"></script>

	<!-- EASY PIE CHART JS -->
	<script src="assets/plugins/easypie-chart/easypiechart.min.js"></script>
	<script src="assets/plugins/easypie-chart/jquery.easypiechart.min.js"></script>

	<!-- KNOB JS -->
	<!--[if IE]>
		<script type="text/javascript" src="assets/plugins/jquery-knob/excanvas.js"></script>
		<![endif]-->
	<script src="assets/plugins/jquery-knob/jquery.knob.js"></script>
	<script src="assets/plugins/jquery-knob/knob.js"></script>

	<!-- FLOT CHART JS -->
	<script src="assets/plugins/flot-chart/jquery.flot.js"></script>
	<script src="assets/plugins/flot-chart/jquery.flot.tooltip.js"></script>
	<script src="assets/plugins/flot-chart/jquery.flot.resize.js"></script>
	<script src="assets/plugins/flot-chart/jquery.flot.selection.js"></script>
	<script src="assets/plugins/flot-chart/jquery.flot.stack.js"></script>
	<script src="assets/plugins/flot-chart/jquery.flot.time.js"></script>

	<!-- MORRIS JS -->
	<script src="assets/plugins/morris-chart/raphael.min.js"></script>
	<script src="assets/plugins/morris-chart/morris.min.js"></script>

	<!-- C3 JS -->
	<script src="assets/plugins/c3-chart/d3.v3.min.js" charset="utf-8"></script>
	<script src="assets/plugins/c3-chart/c3.min.js"></script>

	<!-- MAIN APPS JS -->
	<script src="assets/js/apps.js"></script>

</body>
</html>