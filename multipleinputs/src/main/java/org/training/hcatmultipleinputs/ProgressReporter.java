package org.training.hcatmultipleinputs;

import org.apache.hadoop.mapred.Counters;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.StatusReporter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskInputOutputContext;

class ProgressReporter extends StatusReporter implements Reporter {

  private TaskInputOutputContext context = null;
  private TaskAttemptContext taskAttemptContext = null;

  public ProgressReporter(TaskAttemptContext context) {
    if (context instanceof TaskInputOutputContext) {
      this.context = (TaskInputOutputContext) context;
    } else {
      taskAttemptContext = context;
    }
  }

  @Override
  public void setStatus(String status) {
    if (context != null) {
      context.setStatus(status);
    }
  }

  @Override
  public Counters.Counter getCounter(Enum<?> name) {
    return (context != null) ? (Counters.Counter) context.getCounter(name) : null;
  }

  @Override
  public Counters.Counter getCounter(String group, String name) {
    return (context != null) ? (Counters.Counter) context.getCounter(group, name) : null;
  }

  public void incrCounter(Enum<?> key, long amount) {
    if (context != null) {
      context.getCounter(key).increment(amount);
    }
  }

  public void incrCounter(String group, String counter, long amount) {
    if (context != null) {
      context.getCounter(group, counter).increment(amount);
    }
  }

  public InputSplit getInputSplit() throws UnsupportedOperationException {
    return null;
  }

  public float getProgress() {
    /* Required to build against 0.23 Reporter and StatusReporter. */
    /* TODO: determine the progress. */
    return 0.0f;
  }

  @Override
  public void progress() {
    if (context != null) {
      context.progress();
    } else {
      taskAttemptContext.progress();
    }
  }
}