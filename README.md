# HSienaSimulator
A simulator for the self-reconfiguring procedure of a pub/sub network presented in the following paper:

-  F. Petroni and L. Querzoni:
   HSIENA: a hybrid publish/subscribe system.
   In: Proceedings of the 1st International Workshop on Dependable and Secure Computing for Large-scale 
   Complex Critical Infrastructures (DESEC-LCCI), 2012.
   
Abstract:  The SIENA publish/subscribe system represents a prototypical design for a distributed event 
   notification service implementing the content-based publish/subscribe communication paradigm. 
   A clear shortcoming of SIENA is represented by its static configuration that must be managed and 
   updated by human administrators every time one of its internal processes (brokers) needs to be added 
   or repaired (e.g. due to a crash failure). This problem limits the applicability of SIENA in large complex 
   critical infrastructures where self-adaptation and -configuration are crucial requirements. 
   In this paper we propose HSIENA, a hybrid architecture that complements SIENA by adding the ability to 
   self-reconfigure after broker additions and removals. The architecture has a novel design that mixes the 
   classic SIENA’s distributed architecture with a highly available cloud-based storage service.

The application makes use of JUNG — the Java Universal Network/Graph Framework (http://jung.sourceforge.net).

All the libraries needed are in the dist/lib folder:
- collections-generic-4.01.jar
- jung-algorithms-2.0.1.jar
- jung-api-2.0.1.jar
- jung-graph-impl-2.0.1.jar
- jung-visualization-2.0.1.jar
