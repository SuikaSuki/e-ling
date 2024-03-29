define("eling/component/ui/wizard/1.0.0/dist/wizard-debug", [ "eling/component/core/uicomponent/2.0.0/src/uicomponent", "base", "tools", "handlebars", "templatable", "bootstrap", "./wizard.css", "./wizard.tpl" ], function(require, exports, module) {
    var UIComponent = require("eling/component/core/uicomponent/2.0.0/src/uicomponent");
    require("./wizard.css");
    var Wizard = UIComponent.extend({
        attrs: {
            template: require("./wizard.tpl"),
            autoRender: true
        },
        events: {
            "click .J-next": function() {
                this.next();
                return false;
            },
            "click .J-prev": function() {
                this.prev();
                return false;
            }
        },
        parseElement: function() {
            var model = this.get("model") || {};
            var items = model.items || [];
            for (var i = 0; i < items.length; i++) {
                items[i].index = i + 1;
            }
            model.length = items.length;
            this.set("current", 1);
            this.set("all", items.length);
            Wizard.superclass.parseElement.call(this, arguments);
        },
        afterRender: function() {
            this.element.find(".steps li:first,.step-pane:first").addClass("active");
            var model = this.get("model") || {};
            var items = model.items || [];
            this.element.find(".J-title").text(items[0].text);
        },
        prev: function() {
            var currentIndex = this.get("current");
            var all = this.get("all");
            if (currentIndex > 1) {
                this.element.find(".J-next").addClass("btn-success");
                this.set("current", currentIndex - 1);
                var activeLI = this.element.find("li.active");
                var prevActiveLI = activeLI.prev();
                if (prevActiveLI.hasClass("hidden")) {
                    prevActiveLI.removeClass("hidden");
                    activeLI.parents("ul").find("li:visible:last").addClass("hidden");
                }
                activeLI.removeClass("active");
                prevActiveLI.removeClass("complete").addClass("active");
                if (currentIndex == 2) {
                    this.element.find(".J-prev").removeClass("btn-success");
                }
            }
            var model = this.get("model") || {};
            var items = model.items || [];
            var title = items[this.get("current") - 1].text;
            this.element.find(".J-title").text(title);
            this.element.find(".J-currentIndex").text(this.get("current"));
            this.element.find(".step-pane").removeClass("active");
            this.element.find(".step-pane").eq(this.get("current") - 1).addClass("active");
        },
        next: function() {
            var currentIndex = this.get("current");
            var all = this.get("all");
            if (currentIndex < all) {
                this.element.find(".J-prev").addClass("btn-success");
                this.set("current", currentIndex + 1);
                var activeLI = this.element.find("li.active");
                var nextActiveLI = activeLI.next();
                if (nextActiveLI.hasClass("hidden")) {
                    nextActiveLI.removeClass("hidden");
                    activeLI.parents("ul").find("li:visible:first").addClass("hidden");
                }
                activeLI.addClass("complete").removeClass("active");
                nextActiveLI.addClass("active");
                if (currentIndex == all - 1) {
                    this.element.find(".J-next").removeClass("btn-success");
                }
            }
            var model = this.get("model") || {};
            var items = model.items || [];
            var title = items[this.get("current") - 1].text;
            this.element.find(".J-title").text(title);
            this.element.find(".J-currentIndex").text(this.get("current"));
            this.element.find(".step-pane").removeClass("active");
            this.element.find(".step-pane").eq(this.get("current") - 1).addClass("active");
        },
        //跳转到第一步
        first: function() {
            this.element.find(".step-pane,.steps li").removeClass("active").removeClass("complete").removeClass("hidden");
            this.element.find(".step-pane:first,.steps li:first").addClass("active");
            var model = this.get("model") || {};
            var items = model.items;
            this.element.find(".J-title").text(items[0].text);
            this.element.find(".J-currentIndex").text("1");
            this.set("current", 1);
        },
        reset: function() {
            this.first();
            this.$(".J-wizard-content").removeClass("hidden");
            this.$(".step-pane,.step li").removeClass("active");
            this.$(".step-pane:first").addClass("active");
            this.$(".step-pane li:first").addClass("active");
        }
    });
    module.exports = Wizard;
});

define("eling/component/ui/wizard/1.0.0/dist/wizard.css", [], function() {
    seajs.importStyle('.el-wizard-1{min-height:500px}.el-wizard-1 .stepsContainer{margin-left:30px!important;display:inline-block;vertical-align:middle}.el-wizard-1 .prevContainer,.el-wizard-1 .nextContainer{display:inline-block;vertical-align:middle}.el-wizard-1 .step-content{margin-top:20px!important}.el-wizard-1 .fuelux .wizard{background-color:#f9f9f9;border:1px solid #d4d4d4;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;*zoom:1;-webkit-box-shadow:0 1px 4px rgba(0,0,0,.065);-moz-box-shadow:0 1px 4px rgba(0,0,0,.065);box-shadow:0 1px 4px rgba(0,0,0,.065)}.el-wizard-1 .fuelux .wizard:before,.el-wizard-1 .fuelux .wizard:after{display:table;line-height:0;content:""}.el-wizard-1 .fuelux .wizard:after{clear:both}.el-wizard-1 .fuelux .wizard ul{padding:0;margin:0;list-style:none outside none}.el-wizard-1 .fuelux .wizard ul li{position:relative;float:left;height:46px;padding:0 20px 0 30px;margin:0;font-size:16px;line-height:46px;color:#999;cursor:default;background:#ededed}.el-wizard-1 .fuelux .wizard ul li .chevron{position:absolute;top:0;right:-14px;display:block;border:24px solid transparent;border-right:0;border-left:14px solid #d4d4d4}.el-wizard-1 .fuelux .wizard ul li .chevron:before{position:absolute;top:-24px;right:1px;display:block;border:24px solid transparent;border-right:0;border-left:14px solid #ededed;content:""}.el-wizard-1 .fuelux .wizard ul li.complete{color:#468847;background:#f3f4f5}.el-wizard-1 .fuelux .wizard ul li.complete:hover{cursor:pointer;background:#e7eff8}.el-wizard-1 .fuelux .wizard ul li.complete:hover .chevron:before{border-left:14px solid #e7eff8}.el-wizard-1 .fuelux .wizard ul li.complete .chevron:before{border-left:14px solid #f3f4f5}.el-wizard-1 .fuelux .wizard ul li.active{color:#3a87ad;background:#f1f6fc}.el-wizard-1 .fuelux .wizard ul li.active .chevron:before{border-left:14px solid #f1f6fc}.el-wizard-1 .fuelux .wizard ul li .badge{margin-right:8px}.el-wizard-1 .fuelux .wizard ul li:nth-child(1){z-index:10;padding-left:20px;border-radius:4px 0 0 4px}.el-wizard-1 .fuelux .wizard ul li:nth-child(2){z-index:9}.el-wizard-1 .fuelux .wizard ul li:nth-child(3){z-index:8}.el-wizard-1 .fuelux .wizard ul li:nth-child(4){z-index:7}.el-wizard-1 .fuelux .wizard ul li:nth-child(5){z-index:6}.el-wizard-1 .fuelux .wizard ul li:nth-child(6){z-index:5}.el-wizard-1 .fuelux .wizard ul li:nth-child(7){z-index:4}.el-wizard-1 .fuelux .wizard ul li:nth-child(8){z-index:3}.el-wizard-1 .fuelux .wizard ul li:nth-child(9){z-index:2}.el-wizard-1 .fuelux .wizard ul li:nth-child(10){z-index:1}.el-wizard-1 .fuelux .wizard .actions{float:right;padding-right:15px;line-height:44px;vertical-align:middle}.el-wizard-1 .fuelux .wizard .actions a{margin-right:8px;font-size:12px;line-height:45px}.el-wizard-1 .fuelux .wizard .actions .btn-prev i{margin-right:5px}.el-wizard-1 .fuelux .wizard .actions .btn-next i{margin-left:5px}.el-wizard-1 .fuelux .step-content .step-pane{display:none}.el-wizard-1 .fuelux .step-content .active{display:block}.el-wizard-1 .fuelux .wizard{-webkit-border-radius:0;-moz-border-radius:0;-ms-border-radius:0;-o-border-radius:0;border-radius:0;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;background-color:transparent;border:0}.el-wizard-1 .fuelux .wizard .actions{padding:0}.el-wizard-1 .fuelux .wizard ul li{background-color:#ddd;margin-right:30px;padding:0 20px;color:#424242;font-family:Microsoft YaHei;font-size:20px;position:relative;-webkit-border-radius:0!important;-moz-border-radius:0!important;-ms-border-radius:0!important;-o-border-radius:0!important;border-radius:0!important}.el-wizard-1 .fuelux .wizard ul li.active{background-color:#f34541;color:#fff}.el-wizard-1 .fuelux .wizard ul li.complete{background-color:#49bf67;color:#fff}.el-wizard-1 .fuelux .wizard ul li.complete:hover{background-color:#3eb05b}.el-wizard-1 .fuelux .wizard ul li.complete:before{background-color:#49bf67}.el-wizard-1 .fuelux .wizard ul li:before{content:"";width:30px;height:4px;background-color:#ddd;display:block;position:absolute;top:50%;margin-top:-2px;left:-30px}.el-wizard-1 .fuelux .wizard ul li:first-child:before{display:none}@media print{.el-wizard-1 .fuelux .step-content .step-pane{display:block!important;opacity:1!important}}');
});

define("eling/component/ui/wizard/1.0.0/dist/wizard.tpl", [], '<div class=\'container ui-wizard el-wizard-1\'>\n	<div class=\'row\'>\n		<div class=\'col-xs-12\'>\n			<div class=\'row\' style="min-height: 500px;">\n                <div class=\'col-sm-12\'>\n                	<div class=\'box\'>\n                		<div class=\'box-content box-padding\'>\n                			<div class=\'fuelux\'>\n                				<div class=\'wizard J-wizard-content\'>\n                					<div style="display: inline-block;vertical-align: middle;">\n                						<button class="btn btn-prev J-prev">\n	                						<i class="icon-chevron-left"></i>\n	                					</button>\n                					</div>\n                					<ul class=\'steps\' style="margin-left: 30px;display: inline-block;vertical-align: middle;">\n                						{{#each this.items}}\n               								<li data-target=\'#{{this.id}}\'>\n                							<span class=\'step\'>{{this.index}}</span>\n                						</li>\n                						{{/each}}\n                					</ul>\n                					<div style="display: inline-block;vertical-align: middle;">\n	                					<button class="btn btn-success btn-next J-next">\n	                						<i class="icon-chevron-right"></i>\n	                					</button>\n	                				</div>\n	                				<h2 style="width:30%;margin:0 0 0 10%;!important;display: inline-block;vertical-align: middle;" class="J-title"></h2>\n               						<div style="display: inline-block;vertical-align: middle;">\n               							<h3 class="J-currentIndex" style="margin:0 !important;display: inline-block;vertical-align: middle;">1</h3>\n               							<h3 style="margin:0 !important;display: inline-block;vertical-align: middle;">/</h3>\n               							<h3 style="margin:0 !important;display: inline-block;vertical-align: middle;">{{this.length}}</h3>\n               						</div>\n                				</div>\n                				<div class=\'step-content\'>\n                					<form class="form" style="margin-top:20px; margin-bottom: 0;" method="post" \n                						action="#" accept-charset="UTF-8">\n                						{{#each this.items}}\n                							<div class=\'step-pane\' id=\'{{this.id}}\'>\n                								{{this.content}}\n                							</div>\n                						{{/each}}\n                        			</form>\n                        		</div>\n                      		</div>\n                    	</div>\n                  	</div>\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n                      	');
