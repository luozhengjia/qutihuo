jQuery.fn.imgUploader = function(options) {
	options = options || {max:1};
	var input = this;
	
	// 先构建出上传组件，再隐藏自己
	var uploadBtnId = this.attr('id')+'_imgUploader';
	input.after('<ul class="list-inline"><li class="btn-upload"><a id="'+ uploadBtnId +'" href="#" ><img width="96px" height="96px" src="http://s.demo.shangquanquan.com/static/images/upload.png"/></a></li></ul>');
	input.css({position: "absolute",left: "-10000px"});
                    
	// 获取数据值，渲染出图片节点
	var uploadBtn = $('#'+uploadBtnId).closest("ul").find('.btn-upload');
	if(typeof(input.val()) != "undefined"  && input.val().length > 0){
		$.each( input.val().split(","), function(i, n){
			uploadBtn.before('<li style="position:relative"><button class="close" style="position:absolute;right:8px;">×</button><img class="loaded-img" width="96px" height="96px" src="' + n + '"/></li>')
		});
	}
	
	// 初始化时判断是否需要显示上传组件 
	if (options.max && uploadBtn.closest("ul").find('li').length > options.max) {
		uploadBtn.hide();
    } else {
        uploadBtn.show();
    }
	
    // 初始化上传组件
    var uploader = Qiniu.uploader({
    	'runtimes': 'html5,flash,html4',
    	'browse_button': uploadBtnId,
    	'uptoken_url':'/getUptoken.jhtml',
    	'domain':'http://7xig2s.com1.z0.glb.clouddn.com/',
    	 multi_selection: !(mOxie.Env.OS.toLowerCase()==="ios"),
    	'max_file_size':'2mb',
    	'auto_start':true,
    	'init':{
    		'UploadProgress': function(up, file) {
                // 每个文件上传时,处理相关的事情
            },
            'BeforeUpload': function(up, file) {
                               // 每个文件上传前,处理相关的事情
                        },
            'Error': function(up, err, errTip) {
				//上传出错时,处理相关的事情
            },
    		'FileUploaded':function(up, file, info) {
    			// 向上传组件前插入图片
    			var url = up.getOption('domain') + JSON.parse(info).key;
    			uploadBtn.before('<li style="position:relative"><button type="button" class="close" style="position:absolute;right:8px;">&times;</button><img class="loaded-img" width="84px" height="96px" src="' + url + '"/></li>');
    			
    			// 当上传的图片数超过最大数量，则将上传组件隐藏
    			if(options.max && uploadBtn.closest("ul").find('li').length > options.max){
    				uploadBtn.hide();
    			}
    			
    			// 更新文本框
    			var imgs = new Array(uploadBtn.closest("ul").find('.loaded-img').length);
    			$.each(uploadBtn.closest("ul").find('.loaded-img'), function(i, n){
					imgs[i] = $(n).attr("src");
				});
    			input.val(imgs.join(","));
    			
    			// 回调函数
    			options.callback();
    		}
    	}
    });
    
    //绑定删除事件
    uploadBtn.closest("ul").on("click", ".close", function() {
        $(this).closest("li").remove();
        uploadBtn.show();
        
        // 更新文本框
        var imgs = new Array(uploadBtn.closest("ul").find('.loaded-img').length);
		$.each(uploadBtn.closest("ul").find('.loaded-img'), function(i, n){
			imgs[i] = $(n).attr("src");
		});
		input.val(imgs.join(","));
		
		// 回调函数
    	options.callback();
    })
}