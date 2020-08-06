<div style="text-align: center">
	<a href="https://twitter.com/Kukie_nyan/status/1282739600331440128">
		<img height="300px" style="margin: auto" src="https://pbs.twimg.com/media/Ec003OmUcAAFxGi?format=jpg&name=large">
	</a>
</div>

# T.N.T.

T.N.T. (Toil 'n Trouble) is a D&D 5e character sheet application. Please do not use it to commit crimes.

# Developing Locally

Start up a figwheel-main REPL. This should automatically pull up a
browser window to the app. Changes are hot-reloaded and should be
immediately visible in the front-end. Once state is added from React
then special care will need to be taken for it to load without issue.

## In Emacs

`M-x cider-jack-in-cljs` with tool=lein, repl=figwheel-main, build=dev

## In a terminal

    lein cljs-repl


# Additional Setup

Automated tests

Emacs integration

Confirmation of Windows execution

Devtools? Anything on REPL launch?

Anything special for Firefox? Chrome? native?

# Additional Resources

To use NPM: https://figwheel.org/docs/npm.html

To use a custom-defined server:
https://figwheel.org/docs/your_own_server.html

Advanced compilation for deployment:
https://figwheel.org/docs/advanced_compile.html

Devcards for testing:
https://github.com/bhauman/devcards
http://rigsomelight.com/devcards/#!/devdemos.core
https://paultopia.github.io/posts-output/devcards-testing/


Code-Splitting for targeted loading:
https://clojurescript.org/guides/code-splitting
https://figwheel.org/docs/code_splitting.html
