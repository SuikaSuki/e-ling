define("eling/component/ui/tab/1.0.0/dist/tab",["eling/component/core/uicomponent/2.0.0/src/uicomponent","base","tools","handlebars","templatable","bootstrap","./tab.tpl"],function(a,b,c){var d=a("eling/component/core/uicomponent/2.0.0/src/uicomponent"),e=d.extend({attrs:{template:a("./tab.tpl"),autoRender:!0},initCustAttr:function(){for(var a=[],b=this.get("model")||{},c=b.items||[],d=0;d<c.length;d++)"string"==typeof c[d].content?a.push({id:c[d].id,content:c[d].content}):a.push({id:c[d].id});b.contents=a,"left"==b.layout&&(b.isLeft=!0)},afterRender:function(){this.$("li:first").addClass("active"),this.$(".tab-pane:first").addClass("active")},setActive:function(a){this.$("li").removeClass("active"),this.$("li").eq(a).addClass("active"),this.$(".tab-pane").removeClass("active"),this.$(".tab-pane").eq(a).addClass("active")},getActive:function(){return this.$("li.active").prevAll().size()},addItem:function(a){var b=$("<li></li>"),c=$("<a></a>").attr("data-toggle","tab").attr("href","#"+a.id).text(a.title);b.append(c),this.$("ul.nav-tabs").append(b);var d=$("<div></div>").addClass("tab-pane").attr("id",a.id);$(".tab-content").append(d)},removeItem:function(a){this.$("li").eq(a).remove(),this.$(".tab-content").children().eq(a).remove()},removeItems:function(a,b){var c=this.$(".tab-content").children();b=b||this.$("ul.nav-tabs").find("li").size()-1,this.$("ul.nav-tabs li").each(function(d,e){d>=a&&b>=d&&(e.remove(),c.eq(d).remove())})}});c.exports=e}),define("eling/component/ui/tab/1.0.0/dist/tab.tpl",[],"<div class='container el-tab'>\n	{{#if this.isLeft}}\n	<div class=\"row\">\n		<div class=\"col-sm-12 box\">\n			<div class='box-content'>\n				<div class=\"tabbable tabs-left\">\n					<ul class=\"nav nav-tabs\">\n						{{#each this.items}}\n							<li>\n								<a data-toggle='tab' href='#{{this.id}}'>\n									{{this.title}}\n								</a>\n	                        </li>\n                        {{/each}}\n                    </ul>\n                    <div class=\"tab-content\">\n                    	{{#each this.contents}}\n                    		<div id=\"{{this.id}}\" class=\"tab-pane\">\n                    			<p>{{this.content}}</p>\n                    		</div>\n                    	{{/each}}\n                    </div>\n                </div>\n            </div>\n        </div>\n    </div>\n	{{else}}\n	<div class='row'>\n		<div class='col-sm-12 box'>\n			<div class='box-content'>\n				<div class='tabbable'>\n					<ul class='nav nav-responsive nav-tabs'>\n						{{#each this.items}}\n						<li>\n							<a data-toggle='tab' href='#{{this.id}}'>\n								{{this.title}}\n							</a>\n                        </li>\n                        {{/each}}\n                    </ul>\n                    <div class='tab-content'>\n                    	{{#each this.contents}}\n                    		<div id=\"{{this.id}}\" class=\"tab-pane\">\n                    			<p>{{this.content}}</p>\n                    		</div>\n                    	{{/each}}\n                	</div>\n            	</div>\n       		</div>\n       	</div>\n    </div>\n	{{/if}}\n</div>");