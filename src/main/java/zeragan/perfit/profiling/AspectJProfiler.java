package zeragan.perfit.profiling;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.xml.sax.InputSource;

import zeragan.perfit.core.NodeSource;
import zeragan.perfit.core.NodeSourceAdapter;
import zeragan.perfit.core.configuration.NodeSourceBuilder;
import zeragan.perfit.profiling.time.DefaultTimeSource;

@Aspect
public abstract class AspectJProfiler implements NodeSourceAdapter
{

    private static AspectJProfiler INSTANCE;

    public static AspectJProfiler getInstance()
    {
        return INSTANCE;
    }

    private final Profiler profiler;

    public AspectJProfiler()
    {
        verifyJavaAgent();
        synchronized (this)
        {
            if (INSTANCE != null)
            {
                throw new IllegalStateException("only 1 instance of AspectJProfilerAdapter by jvm");
            }
            INSTANCE = this;

            Profiler profiler;
            InputSource source;
            String configurationFile = System.getProperty("aspectj.profiler.configuration");
            if (configurationFile != null)
            {
                source = new InputSource(configurationFile);
            }
            else
            {
                source = new InputSource(this.getClass().getResourceAsStream("META-INF/profiler.xml"));
            }
            try
            {
                NodeSource tempProfiler = NodeSourceBuilder.build(source);
                if (!(tempProfiler instanceof Profiler))
                {
                    throw new IllegalStateException(
                        "Aspect Profiler Adapter ne peut fonctionner qu'avec une source de type "
                            + Profiler.class.getName());
                }
                profiler = (Profiler) tempProfiler;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                profiler = new Profiler(new DefaultTimeSource());
            }
            this.profiler = profiler;
        }
    }

    private void verifyJavaAgent()
    {
        boolean javaAgentConfigured = false;

        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        for (String argument : arguments)
        {
            if (argument.startsWith("-javaagent:"))
            {
                javaAgentConfigured = true;
            }
        }
        if (!javaAgentConfigured)
        {
            throw new IllegalStateException("La mesure de la performance ne peut pas s'executer car la JVM n'est pas paramétrée, "
                + "ajoutez le paramètre '-javaagent:<chemin vers le jar de l'agent d'instrumentation java>' sur la ligne de commande "
                + "de lancement de la JVM.");
        }
        else
        {
            try
            {
                Class.forName("org.aspectj.weaver.loadtime.Agent");
            }
            catch (ClassNotFoundException e)
            {
                throw new IllegalStateException(
                    "La mesure de la performance ne peut pas s'executer car aspectj n'est pas en dépendance du projet");
            }
        }
    }

    @Pointcut
    public abstract void pointcut();

    @Around("pointcut()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable
    {
        profiler.enter(pjp.getSignature().toLongString());
        try
        {
            Object result = pjp.proceed();
            return result;
        }
        finally
        {
            profiler.exit();
        }
    }

    @Override
    public Profiler getNodeSource()
    {
        return profiler;
    }

}
