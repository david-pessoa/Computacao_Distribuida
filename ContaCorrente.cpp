#include <iostream>
#include <thread>
#include <mutex>

std::mutex mtx;

void depositos(float* salario)
{
    for(int i = 0; i < 10000; i++)
    {
        std::lock_guard<std::mutex> lock(mtx);
        *salario = *salario + 5;
    }
}

void saques(float* salario)
{
    for(int i = 0; i < 10000; i++)
    {
        std::lock_guard<std::mutex> lock(mtx);
        *salario = *salario - 2;
    }
}

int main()
{
    float saldo = 1000; 
    std::thread t1(depositos, &saldo);
    std::thread t2(saques, &saldo);
    t1.join();
    t2.join();
    //depositos(); // realiza uma "infinidade" de depositos, e.g. 2147483000 depositos de 5.0 unidades monetárias
    //saques(); // realiza uma "infinidade" de saques, e.g. 2147483000 saques de 2.0 unidades monetárias
    
    //Preparação: sem condição de corrida
    //Fase 1: com concorrência, mas sem mutex
    //Fase 2: com concorrência e com mutex
    
    
    printf("Saldo final: %.2lf\n", saldo);

    return 0;
}
