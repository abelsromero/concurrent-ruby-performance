package concurrent.ruby.perf;

import org.jruby.Ruby;

public class RubyGemsPreloader {

    private final Ruby rubyRuntime;

    public RubyGemsPreloader(Ruby rubyRuntime) {
        this.rubyRuntime = rubyRuntime;
    }

    public void preloadRequiredLibraries(String name) {
        this.rubyRuntime.evalScriptlet("require '" + name + "'");
    }

}
