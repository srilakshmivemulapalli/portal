var blogsApp=angular.module('BlogsApp', [ 'ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('blogs',{
		url:'/blogs',
		templateUrl:'js/blogs/blogs.html',
		controller:'blogsController'
	})
	.state('newBlog',{
		url:'/newBlog',
		templateUrl:'js/blogs/newBlog.html',
		controller:'blogsController'
	})
		.state('blog',{
		url:'/blog/:blogId',
		templateUrl:'js/blogs/blog.html',
		controller:'blogController'
	})
		.state('myblogs',{
		url:'/myBlogs',
		templateUrl:'js/blogs/myBlogs.html',
		controller:'blogsController'
	})
})    
	