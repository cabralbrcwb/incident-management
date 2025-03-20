/**
 * CAP Service Definitions for Incident Management
 * 
 * This file defines the services for the incident management application:
 * - ProcessorService: Used by support personnel to handle incidents
 * - AdminService: Used by administrators to manage customers and incidents
 */

// Import entities from the schema file
using { sap.capire.incidents as my } from '../db/schema';

/**
 * Service used by support personnel, i.e. the incidents' 'processors'.
 * Provides access to incidents with full CRUD operations and customers with read-only access.
 */
service ProcessorService { 
    /**
     * Incidents that can be processed by support personnel
     */
    entity Incidents as projection on my.Incidents;

    /**
     * Read-only access to customer information
     */
    @readonly
    entity Customers as projection on my.Customers;
}

// Enable draft mode for the Incidents entity to support save-as-draft functionality
annotate ProcessorService.Incidents with @odata.draft.enabled; 

// Require 'support' authorization role to access this service
annotate ProcessorService with @(requires: 'support');

/**
 * Service used by administrators to manage customers and incidents.
 * Provides full CRUD access to both customers and incidents.
 */
service AdminService {
    /**
     * Customer management with full CRUD operations
     */
    entity Customers as projection on my.Customers;
    
    /**
     * Incident management with full CRUD operations
     */
    entity Incidents as projection on my.Incidents;
}

// Require 'admin' authorization role to access this service
annotate AdminService with @(requires: 'admin');