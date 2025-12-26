# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

VentePlats is a Spring Boot food ordering application that allows users to create, manage, and search for dishes. The application provides REST APIs for user and dish management with filtering capabilities.

## Build and Development Commands

### Maven Commands
- **Build project**: `./mvnw clean compile`
- **Run application**: `./mvnw spring-boot:run` 
- **Run tests**: `./mvnw test`
- **Package application**: `./mvnw clean package`

### Database Setup
The application uses MySQL database. Configure connection in `src/main/resources/application.properties`:
- Database: `vente_plats` 
- Default port: 8081
- Update connection details as needed for your environment

## Architecture

### Core Components

**Models** (`src/main/java/com/helene/venteplats/model/`)
- `Plat`: Dish entity with name, type, description, price, availability date, and creator relationship
- `Utilisateur`: User entity with name and birthday, has one-to-many relationship with dishes

**Controllers** (`src/main/java/com/helene/venteplats/controller/`)
- `PlatController`: REST endpoints for dish operations (CRUD, filtering, user-specific operations)
- `UtilisateurController`: User management operations

**Services** (`src/main/java/com/helene/venteplats/service/`)
- `PlatService`: Business logic for dish operations including filtering with Specifications API
- `UtilisateurService`: User business logic

**DTOs** (`src/main/java/com/helene/venteplats/dto/`)
- `PlatDTO`: Data transfer object for dish data
- `CreationPlatDTO`: DTO specifically for dish creation
- `UtilisateurDTO` and `CreationUtilisateurDTO`: User-related DTOs

### Key Features

**Authentication/Authorization**
- Uses `AuthFiltre` for request filtering
- Controllers check `idCurrentUser` header for authorization
- Users can only manage their own dishes

**Search and Filtering**
- Advanced filtering using JPA Specifications in `PlatSpecification`
- Search criteria system with `SearchCriteria` and `CriteresDeRecherche`
- Date-based filtering for dish availability

**Data Layer**
- Spring Data JPA repositories: `PlatRepository` and `UtilisateurRepository`
- Hibernate validation with `@NotNull` constraints
- Automatic timestamp generation with `@CreationTimestamp`

## Code Conventions

- French naming for domain-specific terms (plat, utilisateur)
- Standard Java camelCase for method names
- JPA entity relationships properly mapped
- DTO pattern for API responses
- Service layer for business logic separation
- Repository pattern for data access

## Testing

Test classes are in `src/test/java/com/helene/venteplats/`:
- `PlatServiceTest`: Service layer testing
- `UtilisateurServiceTest`: User service testing  
- `CreationUtilisateurDTOTest`: DTO validation testing