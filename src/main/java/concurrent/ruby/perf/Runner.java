package concurrent.ruby.perf;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.javasupport.JavaEmbedUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        loadConcurrentRuby();
    }

    private static void loadConcurrentRuby() {
        final Ruby ruby = jrubyInstance();
        RubyGemsPreloader rubyGemsPreloader = new RubyGemsPreloader(ruby);
        long init = System.currentTimeMillis();
//        rubyGemsPreloader.preloadRequiredLibraries("concurrent-ruby");
        rubyGemsPreloader.preloadRequiredLibraries("asciidoctor-pdf");
        System.out.println(System.currentTimeMillis() - init);
    }

    private static Ruby jrubyInstance() {
        RubyInstanceConfig config = new RubyInstanceConfig();
        HashMap<String, String> envs = new HashMap<>();
        envs.put("GEM_PATH", new File("build/.gems/:gems").getAbsolutePath());
        new EnvironmentInjector(config)
                .inject(envs);
        return JavaEmbedUtils.initialize(List.of(), config);
    }

}
