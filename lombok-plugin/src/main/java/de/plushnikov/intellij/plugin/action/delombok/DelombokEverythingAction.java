package de.plushnikov.intellij.plugin.action.delombok;

import de.plushnikov.intellij.plugin.processor.clazz.DataProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.EqualsAndHashCodeProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.GetterProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.SetterProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.ToStringProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.ValueProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.WitherProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderClassProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderExperimentalClassProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderExperimentalPreDefinedInnerClassFieldProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderExperimentalPreDefinedInnerClassMethodProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderExperimentalProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderPreDefinedInnerClassFieldProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderPreDefinedInnerClassMethodProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.builder.BuilderProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.constructor.AllArgsConstructorProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.constructor.NoArgsConstructorProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.constructor.RequiredArgsConstructorProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.log.CommonsLogProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.log.Log4j2Processor;
import de.plushnikov.intellij.plugin.processor.clazz.log.Log4jProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.log.LogProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.log.Slf4jProcessor;
import de.plushnikov.intellij.plugin.processor.clazz.log.XSlf4jProcessor;
import de.plushnikov.intellij.plugin.processor.field.DelegateFieldProcessor;
import de.plushnikov.intellij.plugin.processor.field.GetterFieldProcessor;
import de.plushnikov.intellij.plugin.processor.field.SetterFieldProcessor;
import de.plushnikov.intellij.plugin.processor.field.WitherFieldProcessor;
import de.plushnikov.intellij.plugin.processor.method.BuilderClassMethodProcessor;
import de.plushnikov.intellij.plugin.processor.method.BuilderExperimentalClassMethodProcessor;
import de.plushnikov.intellij.plugin.processor.method.BuilderExperimentalMethodProcessor;
import de.plushnikov.intellij.plugin.processor.method.BuilderMethodProcessor;
import de.plushnikov.intellij.plugin.processor.method.DelegateMethodProcessor;

public class DelombokEverythingAction extends BaseDelombokAction {

  public DelombokEverythingAction() {
    super(createHandler());
  }

  private static BaseDelombokHandler createHandler() {
    return new BaseDelombokHandler(true,
        new RequiredArgsConstructorProcessor(), new AllArgsConstructorProcessor(), new NoArgsConstructorProcessor(),
        new DataProcessor(), new GetterProcessor(), new ValueProcessor(), new WitherProcessor(),
        new SetterProcessor(), new EqualsAndHashCodeProcessor(), new ToStringProcessor(),
        new CommonsLogProcessor(), new Log4jProcessor(), new Log4j2Processor(), new LogProcessor(), new Slf4jProcessor(), new XSlf4jProcessor(),
        new GetterFieldProcessor(), new SetterFieldProcessor(), new WitherFieldProcessor(), new DelegateFieldProcessor(),
        new DelegateMethodProcessor(),

        new BuilderPreDefinedInnerClassFieldProcessor(), new BuilderPreDefinedInnerClassMethodProcessor(),
        new BuilderExperimentalPreDefinedInnerClassFieldProcessor(), new BuilderExperimentalPreDefinedInnerClassMethodProcessor(),
        new BuilderClassProcessor(), new BuilderClassMethodProcessor(), new BuilderMethodProcessor(), new BuilderProcessor(),
        new BuilderExperimentalClassProcessor(), new BuilderExperimentalClassMethodProcessor(),
        new BuilderExperimentalMethodProcessor(), new BuilderExperimentalProcessor()
    );
  }

}