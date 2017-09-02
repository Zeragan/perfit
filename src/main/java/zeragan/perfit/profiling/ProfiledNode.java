package zeragan.perfit.profiling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import zeragan.perfit.core.Node;

public final class ProfiledNode implements Serializable, Node {

    private static final long serialVersionUID = 1L;

    private final String threadName;

    private final String nodeName;

    private ProfiledNode parent;

    private List<ProfiledNode> subs = new ArrayList<ProfiledNode>();

    /**
     * Tick for begin of profiling.
     */
    private Long bp;

    /**
     * Tick for begin of node execution.
     */
    private Long b;

    /**
     * Tick for end of node execution.
     */
    private Long e;

    /**
     * Tick for end of profiling.
     */
    private Long ep;

    /**
     * Equals to e - b.
     */
    private Long elapsedTime;

    /**
     * Equals to sum of totalOverTime of all sub-measures.
     */
    private Long totalOverTime;

    /**
     * Equals to elapsedTime - sum of overTime of all sub-measures.
     */
    private Long totalTime;

    /**
     * Equals to totalTime - sum of totalTime of all sub-measures.
     */
    private Long innerTime;

    ProfiledNode(ProfiledNode parent, String threadName, String nodeName) {
        super();
        this.parent = parent;
        this.threadName = threadName;
        this.nodeName = nodeName;
        if (parent != null) {
            if (parent.subs == null) {
                throw new IllegalStateException("parent measure is already closed, can't create sub-measure");
            }
            parent.subs.add(this);
        }
    }

    @Override
    public final String getThreadName() {
        return threadName;
    }

    @Override
    public final String getNodeName() {
        return nodeName;
    }

    /**
     * <p>
     * Return the computed elapsed time for this node.
     * </p>
     * <p>
     * If the node is not yet executed then an {@link IllegalStateException} is thrown.
     * </p>
     * <p>
     * If the node is executed but not finalized, then the elapsed time returned is the time between the start of the execution of the node
     * and the end of the execution of the node, meaning it include overhead induced by sub-nodes without overhead induced by this node. The
     * formula is {@code e - b}.
     * </p>
     * <p>
     * If the node is finalized, then the elapsed time returned is the time between the start of the profiling of the node and the end of
     * the profiling of the node, meaning it include overhead induced by sub-nodes and overhead induced by this node. The formula is
     * {@code ep - bp}.
     * </p>
     *
     * @return
     */
    public final long getElapsedTime() {
        if (ep != null && bp != null) {
            return this.ep - this.bp;
        } else if (elapsedTime != null) {
            return elapsedTime;
        } else {
            throw new IllegalStateException("elapsedTime is not yet available");
        }
    }

    /**
     * <p>
     * Return the computed overhead time for this node.
     * </p>
     * <p>
     * If the node is not yet executed then an {@link IllegalStateException} is thrown.
     * </p>
     * <p>
     * If the node is executed but not finalized, then the overtime returned is the time induced by the profiling of sub-nodes, meaning it
     * doesn't include overtime induced by the profiling of this node.
     * </p>
     * <p>
     * If the node is finalized, then the overtime returned is the time induced by the profiling of sub-nodes and this node.
     * </p>
     *
     * @return
     */
    public final long getTotalOverTime() {
        if (totalOverTime != null && ep != null && e != null && b != null && bp != null) {
            return totalOverTime + getInnerOverTime();
        } else if (totalOverTime != null) {
            return totalOverTime;
        } else {
            throw new IllegalStateException("total overTime is not yet available");
        }
    }

    public final long getInnerOverTime() {
        if (ep != null && e != null && b != null && bp != null) {
            return ep - e + b - bp;
        } else {
            throw new IllegalStateException("inner overTime is not yet available");
        }
    }

    @Override
    public final long getTotalTime() {
        if (totalTime == null) {
            throw new IllegalStateException("totalTime is not yet available");
        }
        return totalTime;
    }

    @Override
    public final long getInnerTime() {
        if (innerTime == null) {
            throw new IllegalStateException("innerTime is not yet available");
        }
        return innerTime;
    }

    @Override
    public final String toString() {
        return "Node [threadName=" + threadName + ", nodeName=" + nodeName + ", bp=" + bp + ", b=" + b + ", e="
                + e + ", ep=" + ep + "]";
    }

    final ProfiledNode getParent() {
        ProfiledNode detached = parent;
        parent = null; // memory release
        return detached;
    }

    final void setBeginProfiling(long tick) {
        this.bp = tick;
    }

    final void setBegin(long tick) {
        this.b = tick;
    }

    final void setEnd(long tick) {
        if (this.b == null || this.bp == null) {
            throw new IllegalStateException("can't call setEnd() measure has not begun yet");
        }
        if (this.b < this.bp) {
            throw new IllegalStateException("this measure is invalid");
        }
        if (subs == null) {
            throw new IllegalStateException("can't call setEnd() measure is already finalized");
        }
        if (tick < this.b) {
            throw new IllegalArgumentException("end tick mut be greater than begin tick");
        }
        this.e = tick;
        this.elapsedTime = this.e - this.b;
        long overTimeSub = 0;
        long totalTimeSub = 0;
        for (ProfiledNode sub : subs) {
            overTimeSub += sub.getTotalOverTime();
            totalTimeSub += sub.getTotalTime();
        }
        subs.clear();
        subs = null; // release memory
        this.totalOverTime = overTimeSub;
        this.totalTime = this.elapsedTime - overTimeSub;
        this.innerTime = this.totalTime - totalTimeSub;
    }

    final void setEndProfiling(long tick) {
        this.ep = tick;
    }

}
