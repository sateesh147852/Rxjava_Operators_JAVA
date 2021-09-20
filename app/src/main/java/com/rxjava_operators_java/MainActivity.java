package com.rxjava_operators_java;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rxjava_operators_java.adapter.PostsAdapter;
import com.rxjava_operators_java.databinding.ActivityMainBinding;
import com.rxjava_operators_java.model.Comment;
import com.rxjava_operators_java.model.Post;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String TAG = "MainActivity";
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;
    private PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeRecyclerView();

        getPostObservable().subscribeOn(Schedulers.io())
                .flatMap(new Function<Post, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(Post post) throws Exception {
                        return getCommentsObservable(post).subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(Post post) {
                        postsAdapter.updatePosts(post);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initializeRecyclerView() {

        postsAdapter = new PostsAdapter();
        binding.rvPosts.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPosts.setAdapter(postsAdapter);

    }

    private Observable<Post> getPostObservable(){

        return ServiceGenerator.getRequestApiInstance()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<Post>, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(List<Post> posts) throws Exception {
                        postsAdapter.setPosts(posts);
                        return Observable.fromIterable(posts);
                    }
                });

    }

    private Observable<Post> getCommentsObservable(Post post){

        return ServiceGenerator.getRequestApiInstance()
                .getComments(post.getId())
                .subscribeOn(Schedulers.io())
                .map(new Function<List<Comment>, Post>() {
                    @Override
                    public Post apply(List<Comment> comments) throws Exception {
                        post.setComments(comments);
                        int delay = (new Random().nextInt(5)) * 1000;
                        Log.i(TAG, "apply: "+delay);
                        Thread.sleep(delay);
                        return post;
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}