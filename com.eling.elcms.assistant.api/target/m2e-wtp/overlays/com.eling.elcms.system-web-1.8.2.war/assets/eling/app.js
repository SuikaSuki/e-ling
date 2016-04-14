define(function(require,exports,module){
	
	//在sea_config中设置了这些参数。之所以不在这里直接设置，是因为eling文件夹下面的东西，maven变量打不进去
	var home = localStorage.getItem("home");
	var theme = localStorage.getItem("theme");
	var city = localStorage.getItem("city");
	
	var store = require("store");
	
	var aw = require("ajaxwrapper");
	var Enums = require("enums");
	
	var $ = require("$");
	var moment = require("momentwrapper");
	var themeUtils = require("theme");
	
	var appConfig = require("./app_config");
	
	//设置全局变量
	window.$ = window.jQuery = $;
	window.moment = moment;
	
	module.exports = {
		initView : function(view){
			seajs.use([view.name],function(View){
				aw.ajax({
					url : "api/user/me",
					data : appConfig.queryUserConfig,
					success : function(data) {
						store.set("user",data);
						
						//正常打开
						new View({
							params : store.deserialize(view.parameters),
							model : appConfig.mainframeConfig
						}).render();
							
					}
				});
			});
		},
		queryEnums : function(){
			aw.ajax({
				url : "api/enums",
				async : false,
				success : function(data){
					Enums.init(data);
				}
			});
		},
		initTheme : function(){
			//设置主题
			themeUtils.init(home,theme);
			$(".J-logo").attr("src",themeUtils.getThemeAttr("logo_left"));
		},
		setCity : function(){
			//缓存城市
			store.set("city",city);
		},
		
		init : function(view){
			this.setCity();
			this.initTheme();
			this.queryEnums();
			this.initView(view);
		}
	};
});