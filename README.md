<div style="text-align: center">
	<a href="https://twitter.com/Kukie_nyan/status/1282739600331440128">
		<img height="300px" style="margin: auto" src="https://pbs.twimg.com/media/Ec003OmUcAAFxGi?format=jpg&name=large">
	</a>
</div>

# T.N.T.

T.N.T. (Toil 'n Trouble) is a D&D 5e character sheet application. Please do not use it to commit crimes.

## Overview

FIXME: Write a paragraph about the library/project and highlight its goals.

## Setup

To get an interactive development environment run:

    npm install
    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
