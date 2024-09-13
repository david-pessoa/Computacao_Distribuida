import grpc
from concurrent import futures
import time
# import the generated classes
import calculator_pb2
import calculator_pb2_grpc

# import the original calculator.py
from calculator import Calculadora, NumeroComplexo

# create a class to define the server functions, derived from
# calculator_pb2_grpc.CalculatorServicer
class CalculatorServicer(calculator_pb2_grpc.CalculatorServicer):
    
    def Soma(self, request, context):
        response = calculator_pb2.Number()
        response.value = Calculadora.soma(request.value)
        return response
    
    def Subtracao(self, request, context):
        response = calculator_pb2.Number()
        response.value = Calculadora.subtracao(request.value)
        return response
    
    def Divisao(self, request, context):
        response = calculator_pb2.Number()
        response.value = Calculadora.div(request.value)
        return response
    
    def Multiplicacao(self, request, context):
        response = calculator_pb2.Number()
        response.value = Calculadora.mult(request.value)
        return response


# create a gRPC server
server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

# use the generated function `add_CalculatorServicer_to_server`
# to add the defined class to the server
calculator_pb2_grpc.add_CalculatorServicer_to_server(
        CalculatorServicer(), server)

# listen on port 50051
print('Starting server. Listening on port 50051.')
server.add_insecure_port('[::]:50051')
server.start()

# since server.start() will not block,
# a sleep-loop is added to keep alive
try:
    while True:
        time.sleep(86400)
except KeyboardInterrupt:
    server.stop(0)