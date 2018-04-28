/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.filebrowserImageUploadUrl = '/uploadCK';
    config.removeDialogTabs = 'image:advanced;image:Link';
    config.language = 'zh-cn';
	config.toolbar = [
		{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', ] },
            { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-',  'Blockquote', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] },
            { name: 'links', items: [ 'Link', 'Unlink'] },
            { name: 'insert', items: [ 'Image', 'Table'] },
            { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
            { name: 'styles', items: [ 'Format', 'Font', 'FontSize' ] }
	];
};