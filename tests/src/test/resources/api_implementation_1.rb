class SampleServiceImplementation
  include Java::com.justinedelson.jrubyosgi.api.SampleService
  
  def doSomething(s)
    s + s
  end
end

SampleServiceImplementation.new