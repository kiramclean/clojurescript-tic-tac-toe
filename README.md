# tic-tac-toe

A [re-frame](https://github.com/Day8/re-frame) application to play tic tac toe, so I could learn a bit about clojure(script).

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
